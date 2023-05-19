package com.rafael.pedidovenda.controller;

import com.rafael.pedidovenda.model.Pedido;

import lombok.Getter;

public class PedidoAlteradoEvent {
	
	@Getter
	private Pedido pedido;
	
	public PedidoAlteradoEvent(Pedido pedido) {
		this.pedido = pedido;
	}

}
