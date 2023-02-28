package com.rafael.pedidovenda.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.rafael.pedidovenda.model.Produto;

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	private String jpql;

	public Produto guardar(Produto produto) {
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		produto = manager.merge(produto);
		
		trx.commit();
		return produto;
	}

	public Produto porSku(String sku) {
		Produto produto = null;
		try {
			jpql = "from Produto where upper(sku) = :sku";
			produto = manager
					.createQuery(jpql, Produto.class)
					.setParameter("sku", sku.toUpperCase())
					.getSingleResult();
			return produto;
		} catch (NoResultException e) {
			return null;
		}
	}

}
