package com.rafael.pedidovenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.rafael.pedidovenda.service.NegocioException;

@SuppressWarnings("deprecation")
@ManagedBean
@RequestScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Integer> itens;

	public CadastroPedidoBean() {
		itens = new ArrayList<>();
		itens.add(1);
	}
	
	public void salvar() {
		throw new NegocioException("Pedido não pode ser salvo, "
				+ "pois ainda não foi implementado. ");
	}

	public List<Integer> getItens() {
		return itens;
	}
}


