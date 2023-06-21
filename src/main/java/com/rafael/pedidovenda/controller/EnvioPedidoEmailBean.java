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
	
	public static final String PATH = "C:/Users/Pinkman/eclipse-workspaces/eclipse-workspace-curso-sistema-comercial/"
			+ "PedidoVenda/src/main/resources/emails/pedido.template";
	
	public void enviarPedido() throws IOException {
		
		MailMessage message = mailer.novaMensagem();
		
		if (pedido != null && pedido.getItens() != null && !pedido.getItens().isEmpty()) {
			
			File file = new File(PATH);
			
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
