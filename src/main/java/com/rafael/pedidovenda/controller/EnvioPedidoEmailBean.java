package com.rafael.pedidovenda.controller;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;
import com.rafael.pedidovenda.model.Pedido;
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
	
	public void enviarPedido() throws IOException {
		MailMessage message = mailer.novaMensagem();
		
		String uri = "//emails//pedido.template";
		
		message.to(pedido.getCliente().getEmail())
			.subject("Pedido " + pedido.getId())
			.bodyHtml(new VelocityTemplate(new File(uri)))
			.put("pedido", pedido)
			.send();
		
//			.bodyHtml(new VelocityTemplate("/emails/pedido.template"))
//			.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/pedido.template")))
			// 06:00
		
		addInfoMessage("Pedido enviado por e-mail com sucesso!");
	}

}
