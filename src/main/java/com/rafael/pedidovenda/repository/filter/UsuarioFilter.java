package com.rafael.pedidovenda.repository.filter;

import java.io.Serializable;

import com.rafael.pedidovenda.model.Grupo;

import lombok.Getter;
import lombok.Setter;

public class UsuarioFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private Grupo grupo;
	
}