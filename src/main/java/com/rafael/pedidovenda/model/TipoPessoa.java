package com.rafael.pedidovenda.model;

import lombok.Getter;

public enum TipoPessoa {

	FISICA("Física"), 
	JURIDICA("Jurídica");

	@Getter
	private String descricao;

	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}
}
