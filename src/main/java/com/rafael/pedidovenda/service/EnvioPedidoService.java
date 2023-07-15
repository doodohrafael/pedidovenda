package com.rafael.pedidovenda.service;

import static com.rafael.pedidovenda.model.StatusPedido.ORCAMENTO;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.repository.Pedidos;
import com.rafael.pedidovenda.util.jpa.Transactional;

public class EnvioPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		if(pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(ORCAMENTO);
		}
		
		pedido.recalcularValorTotal();
		
		if (pedido.getItens().isEmpty()) {
			throw new NegocioException("O pedido deve possuir pelo menos um item.");
		}
		
		if (pedido.isValorTotalNegativo()) {
			throw new NegocioException("Valor total do pedido não pode ser negativo.");
		}
		
		return pedidos.guardar(pedido);
	}

}
