package com.rafael.pedidovenda.repository.filter;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClienteFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	
	private String documentoReceitaFederal;
	
}