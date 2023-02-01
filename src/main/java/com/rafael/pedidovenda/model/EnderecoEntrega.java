package com.rafael.pedidovenda.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnderecoEntrega implements Serializable {

	private static final long serialVersionUID = 1L;

	private String logradouro;
	private String numero;
	private String complemento;
	private String cidade;
	private String uf;
	private String cep;

}