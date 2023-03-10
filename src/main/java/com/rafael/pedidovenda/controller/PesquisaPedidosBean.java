package com.rafael.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.model.StatusPedido;
import com.rafael.pedidovenda.repository.Pedidos;
import com.rafael.pedidovenda.repository.filter.PedidoFilter;

import lombok.Getter;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	@Getter
	private List<Pedido> pedidosFiltrados;
	
	@Getter
	private PedidoFilter filtro;
	
	public PesquisaPedidosBean() {
		filtro = new PedidoFilter();
		pedidosFiltrados = new ArrayList<>();
	}
	
	public void pesquisar() {
		pedidosFiltrados = pedidos.filtrados(filtro);
	}
	
	public StatusPedido[] getStatuses() {
		return StatusPedido.values();
	}
	
}