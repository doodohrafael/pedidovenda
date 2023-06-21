package com.rafael.pedidovenda.controller;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rafael.pedidovenda.model.Cliente;
import com.rafael.pedidovenda.model.TipoPessoa;
import com.rafael.pedidovenda.service.CadastroClienteService;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private Cliente cliente;
	
	@Inject
	private CadastroClienteService cadastroClienteService;
	
	@Getter
	private String mascaraCpfCnpj;
	
	@Getter
	private int sizeCpfCnpj;
	
	public void inicializar() {
		if (cliente == null) {
			limparCampos();
		} else {
			trocarMascara();
		}
	}
	
	public void limparCampos() {
		cliente = new Cliente();
	}

	public void salvar() {
		cliente = cadastroClienteService.salvar(cliente);
		limparCampos();
		addInfoMessage("Produto salvo com sucesso!");
	}
	
	public void trocarMascara() {
		if (cliente.getTipo() != null) {
			if (cliente.getTipo().equals(TipoPessoa.FISICA)) {
				mascaraCpfCnpj = "999.999.999-99";
				sizeCpfCnpj = 14;
			}
			if (cliente.getTipo().equals(TipoPessoa.JURIDICA)) {
				mascaraCpfCnpj = "99.999.999/9999-99";
				sizeCpfCnpj = 18;
			}
		}
	}
	
	public boolean isEditando() {
		return cliente.getId() != null;
	}

	public TipoPessoa[] getTipos() {
		return TipoPessoa.values();
	}
	
}