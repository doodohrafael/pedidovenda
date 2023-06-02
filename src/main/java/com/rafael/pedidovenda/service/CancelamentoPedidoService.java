package com.rafael.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.model.StatusPedido;
import com.rafael.pedidovenda.repository.Pedidos;
import com.rafael.pedidovenda.util.jpa.Transactional;

public class CancelamentoPedidoService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Transactional
	public Pedido cancelar(Pedido pedido) {
		pedido = pedidos.porId(pedido.getId());
		
		if (pedido.isNaoCancelavel()) {
			throw new NegocioException("Pedido não pode ser cancelado com o status "
					+ pedido.getStatus().getDescricao() + ".");
		}
		
		if (pedido.isEmitido()) {
			estoqueService.retornarItensEstoque(pedido);
		}
		
		pedido.setStatus(StatusPedido.CANCELADO);
		
		pedido = pedidos.guardar(pedido);
		
		return pedido;
	}
	
}
