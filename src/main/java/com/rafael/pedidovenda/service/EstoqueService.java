package com.rafael.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.rafael.pedidovenda.model.ItemPedido;
import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.repository.Pedidos;
import com.rafael.pedidovenda.util.jpa.Transactional;


public class EstoqueService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public void baixarItensEstoque(Pedido pedido) {
		pedido = pedidos.porId(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}
	
	@Transactional
	public void retornarItensEstoque(Pedido pedido) {
		pedido = pedidos.porId(pedido.getId());

		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
	}
}