package com.rafael.pedidovenda.controller;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;
import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.service.EnvioPedidoService;
import com.rafael.pedidovenda.util.mail.Mailer;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnvioPedidoService envioPedidoService;
	
	@Inject
	private Mailer mailer;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	private static final String PATH_TEMPLATE_EMAIL = "/emails/pedido.template";
	
	public void enviarPedido() throws IOException {
		removerEAdicionarItemVazioESalvarPedido();
		
		MailMessage message = mailer.novaMensagem();
		
		if (pedido.getItens() != null && !pedido.getItens().isEmpty()) {
			
			File file = new File(getClass().getResource(PATH_TEMPLATE_EMAIL).getFile());
			
			String dataCriacaoFormatada = formatarDataCriacao(pedido.getDataCriacao());
			String dataEntregaFormatada = formatarDataEntrega(pedido.getDataEntrega());
			
			message.to(pedido.getCliente().getEmail())
				.subject("Pedido " + pedido.getId())
				.bodyHtml(new VelocityTemplate(file))
				.put("pedido", pedido)
				.put("numberTool", new NumberTool())
				.put("locale", new Locale("pt", "BR"))
				.put("dataCriacaoFormatada", dataCriacaoFormatada)
				.put("dataEntregaFormatada", dataEntregaFormatada)
				.send();
		
		addInfoMessage("Pedido enviado por e-mail com sucesso!");
		} 
	}
	
	public void removerEAdicionarItemVazioESalvarPedido() {
		try {
			pedido.removerItemVazio();
			pedido = envioPedidoService.salvar(pedido);
			addInfoMessage("Pedido salvo com sucesso!");
		} finally {
			pedido.adicionarItemVazio();
		}
	}
	
	public String formatarDataCriacao(Date data) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return format.format(data);
	}
	
	public String formatarDataEntrega(Date data) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(data);
	}
	
}
