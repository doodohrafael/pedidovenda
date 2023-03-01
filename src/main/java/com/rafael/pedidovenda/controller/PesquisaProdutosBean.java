package com.rafael.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rafael.pedidovenda.model.Produto;
import com.rafael.pedidovenda.repository.Produtos;
import com.rafael.pedidovenda.repository.filter.ProdutoFilter;

import lombok.Getter;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produtos produtos;

	@Getter 
	private List<Produto> produtosFiltrados;
	
	@Getter
	private ProdutoFilter filtro;

	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();
	}
	
	public void pesquisar() {
		produtosFiltrados = produtos.filtrados(filtro);
	}
	
}