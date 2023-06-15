package com.rafael.pedidovenda.controller;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rafael.pedidovenda.model.Grupo;
import com.rafael.pedidovenda.model.Usuario;
import com.rafael.pedidovenda.repository.Usuarios;
import com.rafael.pedidovenda.service.CadastroUsuarioService;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private Usuario usuario;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Getter @Setter
	private List<Grupo> grupos;
	
	public CadastroUsuarioBean() {
		limparCampos();
	}
	
	public void inicializar() {
		grupos = usuarios.grupos();
	}
	
	public void salvar() {
		usuario = cadastroUsuarioService.salvar(usuario);
		limparCampos();
		addInfoMessage("Produto salvo com sucesso!");
	}
	
	public void limparCampos() {
		usuario = new Usuario();
	}
	
	public boolean isEditando() {
		return usuario.getId() != null;
	}
	
}
