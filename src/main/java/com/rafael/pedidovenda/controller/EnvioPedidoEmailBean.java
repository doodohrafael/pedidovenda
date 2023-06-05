package com.rafael.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;
import com.rafael.pedidovenda.model.Pedido;
import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;
import com.rafael.pedidovenda.util.mail.Mailer;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Mailer mailer;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	public void enviarPedido() {
		MailMessage message = mailer.novaMensagem();
		
		message.to(pedido.getCliente().getEmail())
			.subject("Pedido " + pedido.getId())
			.bodyHtml("<strong>Valor total: </strong>" + pedido.getValorTotal())
			.send();
			
		addInfoMessage("Pedido enviado por e-mail com sucesso!");
	}

}
