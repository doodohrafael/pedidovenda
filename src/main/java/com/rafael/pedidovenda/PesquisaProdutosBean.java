package com.rafael.pedidovenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@SuppressWarnings("deprecation")
@ManagedBean
@RequestScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Integer> produtosFiltrados;
	
	public PesquisaProdutosBean() {
		produtosFiltrados = new ArrayList<>();
		for(int i = 0; i < 50; i++) {
			produtosFiltrados.add(i);
			System.out.println(i);
		}
	}
	
	public List<Integer> getProdutosFiltrados() {
		return produtosFiltrados;
	}
}

