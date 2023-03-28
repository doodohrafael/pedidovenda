package com.rafael.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.rafael.pedidovenda.model.Usuario;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public List<Usuario> vendedores() {
		String jpql;
		jpql = "from Usuario";
		return manager.createQuery(jpql, Usuario.class)
				.getResultList();
	}

	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}
}
