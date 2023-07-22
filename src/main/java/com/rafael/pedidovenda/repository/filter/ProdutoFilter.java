package com.rafael.pedidovenda.repository.filter;

import java.io.Serializable;

import com.rafael.pedidovenda.util.validation.SKU;

import lombok.Getter;
import lombok.Setter;

public class ProdutoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sku;
	
	@Getter @Setter
	private String nome;
	
	@SKU
	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		this.sku = sku == null ? null : sku.toUpperCase();
	}
}
