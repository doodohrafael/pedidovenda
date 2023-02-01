package com.rafael.pedidovenda.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	private Long id;
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String cidade;
	private String uf;
	private String cep;
	private Cliente cliente;
	
}
