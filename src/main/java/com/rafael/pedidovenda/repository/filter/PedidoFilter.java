package com.rafael.pedidovenda.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.rafael.pedidovenda.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PedidoFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long numeroDe;
	private Long numeroAte;
	private Date dataCriacaoDe;
	private Date dataCriacaoAte;
	private String nomeVendedor;
	private String nomeCliente;
	private StatusPedido[] statuses;

}
