package com.rafael.pedidovenda.model;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.NONE;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rafael.pedidovenda.service.NegocioException;
import com.rafael.pedidovenda.validation.SKU;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	@Size(max = 80)
	@Column(nullable = false, length = 80)
	private String nome;

	@NotBlank @SKU
	@Column(nullable = false, length = 20, unique = true)
	private String sku;

	@Getter(value = NONE)
	@Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorUnitario;

	@Getter(value = NONE)
	@Column(name = "quantidade_estoque", nullable = false, length = 5)
	private Integer quantidadeEstoque;

	@ManyToOne // subcategoria
	@JoinColumn(nullable = false)
	private Categoria categoria;
	
	@NotNull @Min(0) @Max(value = 9999, message = "tem um valor muito alto")
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	
	@NotNull(message = "é obrigatório")
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	
	@NotNull
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void baixarEstoque(Integer quantidade) {
		int novaQuantidadeEstoque = quantidadeEstoque - quantidade;
		
		if (novaQuantidadeEstoque < 0) {
			throw new NegocioException("Não há disponibilidade no estoque de "
					+ quantidade + " itens do produto " + sku + ".");
		}
		
		setQuantidadeEstoque(novaQuantidadeEstoque);
	}
	
}