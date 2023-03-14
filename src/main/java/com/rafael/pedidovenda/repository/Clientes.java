package com.rafael.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.rafael.pedidovenda.model.Cliente;
import com.rafael.pedidovenda.model.Pedido;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private String jpql;

	public List<Cliente> porNome(String nome) {
		jpql = "from Cliente where upper(nome) like :nome";
		return manager.createQuery(jpql, Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}

	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}

	public Pedido guardar(Pedido pedido) {
		return manager.merge(pedido);
	}

}