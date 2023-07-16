package com.rafael.pedidovenda.controller;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;
import static com.rafael.pedidovenda.util.jsf.FacesUtil.isNotPostback;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import com.rafael.pedidovenda.model.Cliente;
import com.rafael.pedidovenda.model.EnderecoEntrega;
import com.rafael.pedidovenda.model.EnderecoViaCep;
import com.rafael.pedidovenda.model.FormaPagamento;
import com.rafael.pedidovenda.model.ItemPedido;
import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.model.Produto;
import com.rafael.pedidovenda.model.Usuario;
import com.rafael.pedidovenda.repository.Clientes;
import com.rafael.pedidovenda.repository.Produtos;
import com.rafael.pedidovenda.repository.Usuarios;
import com.rafael.pedidovenda.service.CadastroPedidoService;
import com.rafael.pedidovenda.service.ViaCepService;
import com.rafael.pedidovenda.validation.SKU;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private String cep;
	
	@Inject
	private ViaCepService viaCepService;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private Produtos produtos;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private Usuarios usuarios;

	@Produces 
	@PedidoEdicao 
	@Getter @Setter
	private Pedido pedido;
	
	@Getter @Setter
	private Produto produtoLinhaEditavel;
	
	@Getter @Setter @SKU
	private String sku;
	
	@Getter
	private List<Usuario> vendedores;

	public CadastroPedidoBean() {
		limparCampos();
	}
	
	public void inicializar() {
		if(isNotPostback()) {
			if(pedido != null && pedido.getId() == null) {
				pedido.setEnderecoEntrega(new EnderecoEntrega());
			}
			vendedores = usuarios.vendedores();
			pedido.adicionarItemVazio();
			recalcularPedido();
		}
		
	}
	
	public void limparCampos() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public void pedidoAlterado(@Observes PedidoAlteradoEvent event) {
		this.pedido = event.getPedido();
	}

	public void salvar() {
		try {
			pedido.removerItemVazio();
			pedido = cadastroPedidoService.salvar(pedido);
			addInfoMessage("Pedido salvo com sucesso!");
		} finally {
			pedido.adicionarItemVazio();
		}
	}
	
	public List<Cliente> completarCliente(String nome) {
		return clientes.porNome(nome);
	}
	
	public List<Produto> completarProduto(String nome) {
		return produtos.porNome(nome);
	}
	
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}
	
	public boolean isEditando() {
		return pedido.getId() != null;
	}
	
	public void recalcularPedido() {
		if(pedido != null) {
			pedido.recalcularValorTotal();
		}
	}
	
	public void carregarProdutoPorSku() {
		if (isNotEmpty(sku)) {
			produtoLinhaEditavel = produtos.porSku(sku);
			carregarProdutoLinhaEditavel();
		}
	}
	
	public void carregarProdutoLinhaEditavel() {
		if (existeItemComProduto(produtoLinhaEditavel)) {
			addInfoMessage("Já existe um item no pedido com o produto informado.");
		} else {
			ItemPedido item = pedido.getItens().get(0);
			if (produtoLinhaEditavel != null) {
				item.setProduto(produtoLinhaEditavel);
				item.setValorUnitario(produtoLinhaEditavel.getValorUnitario());

				pedido.adicionarItemVazio();
				produtoLinhaEditavel = null;
				sku = null;
				pedido.recalcularValorTotal();
			}
		}
	}
	
	private boolean existeItemComProduto(Produto produtoLinhaEditavel) {
		boolean existeItem = false;
		
		for(ItemPedido item : pedido.getItens()) {
			if (produtoLinhaEditavel != null && 
					produtoLinhaEditavel.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}
	
	public void atualizarOuRemoverQuantidadeDoItem(ItemPedido item, int linha) {
		if(item.getQuantidade() < 1) {
			if(linha == 0) {
				item.setQuantidade(1);
			} else {
				pedido.getItens().remove(linha);
			}
		}
		
		pedido.recalcularValorTotal();
	}
	
	public void buscarEndereco() {
		String cepSemTraco = cep.replace("-", "");
		EnderecoViaCep endereco = viaCepService.buscarEnderecoPorCep(cepSemTraco);
		
		// tenho que disabilitar os campos e habitar quando um cep for encontrado.
		pedido.getEnderecoEntrega().setCep(endereco.getCep());
		pedido.getEnderecoEntrega().setLogradouro(endereco.getLogradouro());
		pedido.getEnderecoEntrega().setUf(endereco.getUf());
		pedido.getEnderecoEntrega().setCidade(endereco.getLocalidade());
		pedido.getEnderecoEntrega().setBairro(endereco.getBairro());
	}
	
}