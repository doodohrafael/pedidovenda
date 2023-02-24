package com.rafael.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.rafael.pedidovenda.model.Categoria;

public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	private String jpql;
	
	public List<Categoria> raizes() {
		jpql = "from Categoria";
		return manager.createQuery(jpql, 
				Categoria.class).getResultList();
	}

	public Categoria porId(Long id) {
		return manager.find(Categoria.class, id);
	}
	
}
