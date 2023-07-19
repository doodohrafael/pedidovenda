package com.rafael.pedidovenda.model;

import lombok.Getter;

public enum FormaPagamento {

	PIX("Pix", "pix"),
	DINHEIRO("Dinheiro", "dinheiro"), 
	CARTAO_CREDITO("Cartão de Crédito", "cartao-credito"), 
	CARTAO_DEBITO("Cartão de Dédito", "cartao-debito"), 
	BOLETO_BANCARIO("Boleto Bancário", "boleto-bancario"), 
	DEPOSITO_BANCARIO("Depósito Bancário", "deposito-bancario"),
	CHEQUE("Cheque", "cheque");
	
	@Getter
	private String descricao;
	
	@Getter
	private String nomeIconePagamento;
	
	FormaPagamento(String descricao, String nomeIconePagamento) {
		this.descricao = descricao;
		this.nomeIconePagamento = nomeIconePagamento;
	}

}
