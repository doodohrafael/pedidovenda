package com.rafael.pedidovenda.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long id;

	private String nome;
	private String descricao;

}