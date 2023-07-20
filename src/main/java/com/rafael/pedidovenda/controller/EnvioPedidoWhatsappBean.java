package com.rafael.pedidovenda.controller;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addInfoMessage;
import static com.rafael.pedidovenda.util.Formatador.formatarTelefoneCliente;
import static com.rafael.pedidovenda.util.Formatador.formatarData;
import static com.rafael.pedidovenda.util.Formatador.formatarDataEHora;
import static com.rafael.pedidovenda.util.Formatador.formatarMoeda;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rafael.pedidovenda.model.ItemPedido;
import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.service.EnvioPedidoService;

import lombok.Setter;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Named
@RequestScoped
public class EnvioPedidoWhatsappBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final String API = "https://api.ultramsg.com/";
	private static final String INSTANCE_ID = "instance54982";
	private static final String TOKEN = "2jg9ty7qlclb348u";
	
	private static final String BREAK = "\n";
	
	@Setter
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	@Inject
	private EnvioPedidoService envioPedidoService;
	
	public void enviarPedido() throws IOException {
		removerEAdicionarItemVazioESalvarPedido();
		
		OkHttpClient client = new OkHttpClient();
		RequestBody body = new FormBody.Builder() 
					.add("token", TOKEN)
					.add("to", formatarTelefoneCliente(pedido.getCliente().getTelefone()))
					.add("body", mensagemDeTextoParaWhatsapp())
		            .build();

		Request request = new Request.Builder()
		  .url(API + INSTANCE_ID + "/messages/chat")
		  .post(body)
		  .addHeader("content-type", "application/x-www-form-urlencoded")
		  .build();

		client.newCall(request).execute();
		addInfoMessage("Pedido enviado por Whatsapp com sucesso!");
	}
	
	public String mensagemDeTextoParaWhatsapp() {
		String msg = "";
		msg = "Pedido #" + pedido.getId() + " realizado com sucesso!" + BREAK + BREAK +
				
				"DETALHES DA ENTREGA" + BREAK + BREAK +
					"Nome do destinatário: " + pedido.getCliente().getNome() + BREAK +
					"Número de telefone: " + pedido.getCliente().getTelefone() + BREAK +
					"Endereço de entrega: " + pedido.getEnderecoEntrega().getLogradouro() + ", " +
						pedido.getEnderecoEntrega().getNumero() + " - " +
						pedido.getEnderecoEntrega().getUf() + ", " +
						pedido.getEnderecoEntrega().getCidade() + ", " +
						pedido.getEnderecoEntrega().getBairro() + ", " +
						pedido.getEnderecoEntrega().getCep() + BREAK + 
					
				BREAK + BREAK + 
					
				"DETALHES DO PEDIDO" + BREAK + BREAK + 
					"Nome do cliente: " + pedido.getCliente().getNome() + BREAK +
					"Status do pedido: " + pedido.getStatus().getDescricao() + BREAK +
					"Data e hora do pedido: " + formatarDataEHora(pedido.getDataCriacao()) + BREAK +
					"E-mail do cliente: " + pedido.getCliente().getEmail() + BREAK + 
					"Valor do desconto: " + formatarMoeda(pedido.getValorDesconto()) + BREAK +
					"Valor do frete: " + formatarMoeda(pedido.getValorFrete()) + BREAK +
					"Valor total: " + formatarMoeda(pedido.getValorTotal()) + BREAK +
					"Forma de pagamento: " + pedido.getFormaPagamento().getDescricao() + BREAK +
					"Data de entrega: " + formatarData(pedido.getDataEntrega()) + BREAK + 
					
				BREAK + BREAK + 
				
				"ITENS DO PEDIDO" + BREAK + BREAK;
		
				for(ItemPedido item : pedido.getItens()) {
					if(item.getId() != null) {
						msg += "Produto: " + item.getProduto().getNome() + BREAK +
							   "Valor unitário: " + formatarMoeda(item.getProduto().getValorUnitario()) + BREAK +
							   "Quantidade: " + item.getQuantidade() + 
							   BREAK + BREAK;
					}
				}
		
		return msg;
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
	
}
