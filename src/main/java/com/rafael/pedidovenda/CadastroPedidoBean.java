package com.rafael.pedidovenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.rafael.pedidovenda.model.EnderecoEntrega;
import com.rafael.pedidovenda.model.Pedido;

import lombok.Getter;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	private Pedido pedido;

	@Getter
	private List<Integer> itens;

	public CadastroPedidoBean() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
		itens = new ArrayList<>();
		itens.add(1);
	}

	public void salvar() {
	}

}