package com.rafael.pedidovenda.controller;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;
import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.service.EnvioPedidoEmailService;
import com.rafael.pedidovenda.util.mail.Mailer;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnvioPedidoEmailService envioPedidoEmailService;
	
	@Inject
	private Mailer mailer;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	public static final String PATH = "/emails/pedido.template";
	
	public void enviarPedido() throws IOException {
		
		try {
			pedido.removerItemVazio();
			pedido = envioPedidoEmailService.salvar(pedido);
			addInfoMessage("Pedido salvo com sucesso!");
		} finally {
			pedido.adicionarItemVazio();
		}
		
		MailMessage message = mailer.novaMensagem();
		
		if (pedido.getItens() != null && !pedido.getItens().isEmpty()) {
			
			File file = new File(getClass().getResource(PATH).getFile());
			
			message.to(pedido.getCliente().getEmail())
				.subject("Pedido " + pedido.getId())
				.bodyHtml(new VelocityTemplate(file))
				.put("pedido", pedido)
				.put("numberTool", new NumberTool())
				.put("locale", new Locale("pt", "BR"))
				.send();
		
		addInfoMessage("Pedido enviado por e-mail com sucesso!");
		} 
	}
	
}
