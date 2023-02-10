package com.rafael.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@SuppressWarnings("deprecation")
@ManagedBean
@RequestScoped
public class PesquisaPedidosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Integer> pedidosFiltrados;
	
	public PesquisaPedidosBean() {
		pedidosFiltrados = new ArrayList<>();
		for(int i = 0; i < 50; i++) {
			pedidosFiltrados.add(i);
			System.out.println(i);
		}
	}
	
	public List<Integer> getPedidosFiltrados() {
		return pedidosFiltrados;
	}
}

