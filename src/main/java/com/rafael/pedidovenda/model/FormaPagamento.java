package com.rafael.pedidovenda.model;

import lombok.Getter;

public enum FormaPagamento {

	PIX("Pix"),
	DINHEIRO("Dinheiro"), 
	CARTAO_CREDITO("Cartão de Crédito"), 
	CARTAO_DEBITO("Cartão de Dédito"), 
	BOLETO_BANCARIO("Boleto Bancário"), 
	DEPOSITO_BANCARIO("Depósito Bancário"),
	CHEQUE("Cheque");
	
	@Getter
	private String descricao;
	
	FormaPagamento(String descricao) {
		this.descricao = descricao;
	}

}
