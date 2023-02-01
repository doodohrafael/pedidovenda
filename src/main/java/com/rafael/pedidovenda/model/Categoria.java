package com.rafael.pedidovenda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long id;

	private String descricao;
	private Categoria categoriaPai;
	private List<Categoria> subcategorias = new ArrayList<>();

}