package com.rafael.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rafael.pedidovenda.model.Categoria;
import com.rafael.pedidovenda.model.Produto;
import com.rafael.pedidovenda.repository.Categorias;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categorias categorias;

	private Produto produto;
	
	private List<Categoria> categoriasRaizes;
	
	public CadastroProdutoBean() {
		produto = new Produto();
	}
	
	public void inicializar() {
		categoriasRaizes = categorias.raizes();
	}

	public void salvar() {
	}

	public Produto getProduto() {
		return produto;
	}
	
	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}
}
