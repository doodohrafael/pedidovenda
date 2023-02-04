
package com.rafael.pedidovenda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, length = 255)
	private String email;
	
	@Column(name = "doc_receita_federal", nullable = false, length = 14)
	private String documentoReceitaFederal;
	
	@Enumerated(STRING)
	@Column(nullable = false, length = 10)
	private TipoPessoa tipo;
	
	@OneToMany(mappedBy = "cliente", cascade = ALL)
	private List<Endereco> enderecos = new ArrayList<>();

}
