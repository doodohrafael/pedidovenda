package com.rafael.pedidovenda.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "endereco")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false, length = 150)
	private String logradouro;

	@Column(nullable = false, length = 20)
	private String numero;

	@Column(length = 150)
	private String complemento;

	@Column(nullable = false, length = 60)
	private String cidade;

	@Column(nullable = false, length = 60)
	private String uf;

	@Column(nullable = false, length = 9)
	private String cep;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente cliente;

}
