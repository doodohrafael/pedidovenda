package com.rafael.pedidovenda.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long id;
	
	private Integer quantidade;
	private BigDecimal valorUnitario;
	private Produto produto;
	private Pedido pedido;

}