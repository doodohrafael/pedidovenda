package com.rafael.pedidovenda.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataValor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date data;
	private BigDecimal valor;

}
