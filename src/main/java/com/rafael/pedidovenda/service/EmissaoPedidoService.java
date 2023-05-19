package com.rafael.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.repository.Pedidos;
import com.rafael.pedidovenda.util.jpa.Transactional;

import static com.rafael.pedidovenda.model.StatusPedido.EMITIDO;

public class EmissaoPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
		pedido = cadastroPedidoService.salvar(pedido);
		
		if(pedido.isNaoEmissivel()) {
			throw new NegocioException("Pedido não pode ser emitido com o status "
					+ pedido.getStatus().getDescricao() + ".");
		}
		
		estoqueService.baixarItensEstoque(pedido);
		
		pedido.setStatus(EMITIDO);
		
		pedido = pedidos.guardar(pedido);
		
		return pedido;
	}
}