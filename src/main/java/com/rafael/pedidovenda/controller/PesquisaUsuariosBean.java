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
import com.rafael.pedidovenda.repository.filter.UsuarioFilter;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject @Getter
	private UsuarioFilter filtro;
	
	@Getter
	private List<Usuario> usuariosFiltrados;
	
	@Getter @Setter
	private Usuario usuarioSelecionado;
	
	@Getter @Setter
	private List<Grupo> grupos;
	
	public void preRender() {
		grupos = usuarios.grupos();
	}
	
	public void pesquisar() {
		usuariosFiltrados = usuarios.filtrados(filtro);
	}
	
	public void excluir() {
		usuarios.remover(usuarioSelecionado);
		usuariosFiltrados.remove(usuarioSelecionado);
		addInfoMessage("Usuário " + usuarioSelecionado.getNome() 
		+ " excluído com sucesso!");
	}

}
