package com.rafael.pedidovenda.controller;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.service.EnvioPedidoService;
import com.telesign.MessagingClient;
import static java.lang.String.format; 

@Named
@RequestScoped
public class EnvioPedidoSmsBean implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnvioPedidoService envioPedidoService;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	private static final String COSTUMER_ID = "D9035502-E6A4-4652-8657-8250B5EA2364";
	private static final String API_KEY = 
			"+diu/tO35e6lZIExMTGI7VxSqmq1hui6MgHJPmkrsMnWKkY4vssbrJ8ODUw82UEI0epURNVTUSDra77hxnmdAw==";
	
	private static final String PREFIXO_PAIS = "55";
	private static final String MENSAGEM_SMS = "Opa, '%s'. Seu pedido #'%s' na Reboot foi feito com sucesso! "
			+ "Acesse seu e-mail para mais informações.";
	
	public void enviarPedido() {
		removerEAdicionarItemVazioESalvarPedido();
		
		try {
			MessagingClient messagingClient = new MessagingClient(COSTUMER_ID, API_KEY);
			
			String telefoneSemMascara = formatarTelefoneCliente(pedido.getCliente().getTelefone());
			
			messagingClient.message(
					telefoneSemMascara, format(MENSAGEM_SMS, 
							pedido.getCliente().getNome(), pedido.getId()), "ARN", null);
			
			addInfoMessage("Pedido enviado por SMS com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public String formatarTelefoneCliente(String telefoneSemMascara) {
		telefoneSemMascara = pedido.getCliente().getTelefone()
				.replace("(", "")
				.replace(")", "")
				.replace(" ", "")
				.replace("-", "");
		return PREFIXO_PAIS + telefoneSemMascara;
	}
	
}
