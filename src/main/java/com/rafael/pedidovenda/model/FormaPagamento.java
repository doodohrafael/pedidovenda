package com.rafael.pedidovenda.model;

import lombok.Getter;

public enum FormaPagamento {

	DINHEIRO("Dinheiro"), 
	CARTAO_CREDITO("Cartão de Crédito"), 
	CARTAO_DEBITO("Cartão de Dédito"), 
	CHEQUE("Cheque"), 
	BOLETO_BANCARIO("Boleto Bancário"), 
	DEPOSITO_BANCARIO("Depósito Bancário"),
	PIX("Pix");
	
	@Getter
	private String descricao;
	
	FormaPagamento(String descricao) {
		this.descricao = descricao;
	}

}
