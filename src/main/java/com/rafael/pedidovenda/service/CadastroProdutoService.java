package com.rafael.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.rafael.pedidovenda.model.Produto;
import com.rafael.pedidovenda.repository.Produtos;
import com.rafael.pedidovenda.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;
	
	@Transactional
	public Produto salvar(Produto produto) {
		Produto produtoExistente = produtos.porSku(produto.getSku());
		
		if(produtoExistente != null && !produtoExistente.equals(produto)) {
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}
		
		produto.setSku(produto.getSku().toUpperCase());		
		return produtos.guardar(produto);
	}

}
