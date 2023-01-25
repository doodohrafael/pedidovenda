package com.rafael.pedidovenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@SuppressWarnings("deprecation")
@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Integer> produtosFiltrados;
	private Integer produtoSelecionado;
	
	public PesquisaProdutosBean() {
		produtosFiltrados = new ArrayList<>();
		for(int i = 0; i < 50; i++) {
			produtosFiltrados.add(i);
			System.out.println(i);
		}
	}
	
	public void excluirProduto() {
		produtosFiltrados.remove(produtoSelecionado);
	}
	
	public List<Integer> getProdutosFiltrados() {
		return produtosFiltrados;
	}
	
	public Integer getProdutoSelecionado() {
		return produtoSelecionado;
	}
	
	public void setProdutoSelecionado(Integer produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
}