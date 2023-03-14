package com.rafael.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.rafael.pedidovenda.model.Pedido;
import static com.rafael.pedidovenda.model.StatusPedido.ORCAMENTO;
import com.rafael.pedidovenda.repository.Clientes;
import com.rafael.pedidovenda.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		if(pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(ORCAMENTO);
		}
		pedido = clientes.guardar(pedido);
		return pedido;
	}
}
