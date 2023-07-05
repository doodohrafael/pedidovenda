package com.rafael.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.rafael.pedidovenda.model.Categoria;
import com.rafael.pedidovenda.model.Produto;
import com.rafael.pedidovenda.repository.Categorias;
import com.rafael.pedidovenda.service.CadastroProdutoService;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;
import static com.rafael.pedidovenda.util.jsf.FacesUtil.isNotPostback;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;

	@Getter @Setter
	private Produto produto;
	
	@NotNull @Getter @Setter 
	private Categoria categoriaPai;
	
	@Getter
	private List<Categoria> categoriasRaizes;
	
	@Getter
	private List<Categoria> subcategorias;
	
	public CadastroProdutoBean() {
		limparCampos();
	}

	public void inicializar() {
		if(isNotPostback()) {
			categoriasRaizes = categorias.raizes();
			
			if(categoriaPai != null) {
				carregarSubcategorias();
			}
		}
	}
	
	private void limparCampos() {
		produto = new Produto();
		subcategorias = new ArrayList<>();
	}

	public void salvar() {
		produto = cadastroProdutoService.salvar(produto);
		limparCampos();
		addInfoMessage("Produto salvo com sucesso!");
	}
	
	public void carregarSubcategorias() {
		subcategorias = categorias.subcategorias(categoriaPai);
	}
	
	public boolean isEditando() {
		return produto.getId() != null;
	}
	
}
