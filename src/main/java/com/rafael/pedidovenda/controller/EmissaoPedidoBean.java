package com.rafael.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.service.EmissaoPedidoService;
import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;

@Named
@RequestScoped
public class EmissaoPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmissaoPedidoService emissaoPedidoService;

	@Inject 
	@PedidoEdicao 
	private Pedido pedido;
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	public void emitirPedido() {
		pedido.removerItemVazio();
		
		try {
			pedido = emissaoPedidoService.emitir(pedido);
			pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(pedido));
			
			addInfoMessage("Pedido emitido com sucesso!");
		} finally {
			pedido.adicionarItemVazio();
		}
	}
}
