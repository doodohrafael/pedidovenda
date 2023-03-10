package com.rafael.pedidovenda.model;

import lombok.Getter;

public enum StatusPedido {
	
	ORCAMENTO("Orçamento"), 
	EMITIDO("Emitido"), 
	CANCELADO("Cancelado");
	
	@Getter
	private String descricao;
	
	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

}
