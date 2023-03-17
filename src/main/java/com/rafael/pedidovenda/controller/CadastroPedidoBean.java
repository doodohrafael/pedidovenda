package com.rafael.pedidovenda.controller;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;
import static com.rafael.pedidovenda.util.jsf.FacesUtil.isNotPostback;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rafael.pedidovenda.model.Cliente;
import com.rafael.pedidovenda.model.EnderecoEntrega;
import com.rafael.pedidovenda.model.FormaPagamento;
import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.model.Usuario;
import com.rafael.pedidovenda.repository.Clientes;
import com.rafael.pedidovenda.repository.Usuarios;
import com.rafael.pedidovenda.service.CadastroPedidoService;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private Usuarios usuarios;

	@Getter @Setter
	private Pedido pedido;
	
	@Getter
	private List<Usuario> vendedores;

	public CadastroPedidoBean() {
		limparCampos();
	}
	
	public void inicializar() {
		if(isNotPostback()) {
			vendedores = usuarios.vendedores();
		}
	}
	
	public void limparCampos() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}

	public void salvar() {
		this.pedido = cadastroPedidoService.salvar(this.pedido);
		addInfoMessage("Pedido salvo com sucesso!");
	}
	
	public List<Cliente> completarCliente(String nome) {
		return this.clientes.porNome(nome);
	}
	
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}
	
	public boolean isEditando() {
		return this.pedido.getId() != null;
	}
	
	public void recalcularPedido() {
		this.pedido.recalcularValorTotal();
//		this.pedido.setValorTotal(this.pedido.getValorSubtotal()
//				.add(this.pedido.getValorFrete()));
	}

}