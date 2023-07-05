package com.rafael.pedidovenda.controller;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;
import static com.rafael.pedidovenda.util.jsf.FacesUtil.addErrorMessage;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.service.CancelamentoPedidoService;
import com.rafael.pedidovenda.service.NegocioException;

@Named
@RequestScoped
public class CancelamentoPedidoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CancelamentoPedidoService cancelamentoPedidoService;

	@Inject 
	@PedidoEdicao 
	private Pedido pedido;
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	public void cancelarPedido() {
		try {
			pedido = cancelamentoPedidoService.cancelar(pedido);
			pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(pedido));
			
			addInfoMessage("Pedido cancelado com sucesso!");
		} catch (NegocioException ne) {
			addErrorMessage(ne.getMessage());
		}
	}

}