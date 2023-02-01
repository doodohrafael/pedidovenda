package com.rafael.pedidovenda.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long id;

	private String nome;
	private String sku;
	private BigDecimal valorUnitario;
	private Integer quantidadeEstoque;
	private Categoria categoria;

}