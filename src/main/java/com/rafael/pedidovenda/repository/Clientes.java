package com.rafael.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.rafael.pedidovenda.model.Cliente;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public List<Cliente> porNome(String nome) {
		String jpql;
		jpql = "from Cliente where upper(nome) like :nome";
		return manager.createQuery(jpql, Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}

	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}

	public Cliente guardar(Cliente cliente) {
		return manager.merge(cliente);
	}

}