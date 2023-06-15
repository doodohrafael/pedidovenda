package com.rafael.pedidovenda.controller;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rafael.pedidovenda.model.Cliente;
import com.rafael.pedidovenda.model.TipoPessoa;
import com.rafael.pedidovenda.repository.Clientes;
import com.rafael.pedidovenda.repository.filter.ClienteFilter;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Cliente cliente;
	
	@Getter @Setter
	private Cliente clienteSelecionado;
	
	@Getter
	private ClienteFilter filtro;
	
	@Inject
	private Clientes clientes;
	
	@Getter
	private List<Cliente> clientesFiltrados;
	
	@Getter
	private String mascaraCpfCnpj;
	
	@Getter
	private int sizeCpfCnpj;
	
	@Getter 
	private boolean inserirDocumento = false;
	
	public PesquisaClientesBean() {
		limparCampos();
		filtro = new ClienteFilter();
	}
	
	public void pesquisar() {
		clientesFiltrados = clientes.filtrados(filtro);
	}
	
	public void excluir() {
		clientes.remover(clienteSelecionado);
		clientesFiltrados.remove(clienteSelecionado);
		addInfoMessage("Cliente " + clienteSelecionado.getNome() 
		+ " excluído com sucesso!");
	}
	
	public void trocarMascara() {
		if (cliente.getTipo().equals(TipoPessoa.FISICA)) {
			mascaraCpfCnpj = "999.999.999-99";
			sizeCpfCnpj = 14;
		}
		if (cliente.getTipo().equals(TipoPessoa.JURIDICA)) {
			mascaraCpfCnpj = "99.999.999/9999-99";
			sizeCpfCnpj = 18;
		}
	}
	
	private void limparCampos() {
		cliente = new Cliente();		
	}
	
	public boolean isSelecionado() {
		return cliente.getTipo() != null;
	}
	
	public TipoPessoa[] getTipos() {
		return TipoPessoa.values();
	}

}
