package com.rafael.pedidovenda.repository;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.rafael.pedidovenda.model.Cliente;
import com.rafael.pedidovenda.model.TipoPessoa;
import com.rafael.pedidovenda.repository.filter.ClienteFilter;
import com.rafael.pedidovenda.service.NegocioException;
import com.rafael.pedidovenda.util.jpa.Transactional;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private String jpql;
	
	private Cliente cliente;
	
	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}

	public Cliente guardar(Cliente cliente) {
		return manager.merge(cliente);
	}
	
	public List<Cliente> porNome(String nome) {
		jpql = "from Cliente where upper(nome) like :nome";
		return manager.createQuery(jpql, Cliente.class)
				.setParameter("nome", "%" + nome.toUpperCase() + "%")
				.getResultList();
	}
	
	public Cliente porTipoPessoa(TipoPessoa tipoPessoa) {
		jpql = "from Cliente where upper(tipo) like :tipoPessoa";
		return manager.createQuery(jpql, Cliente.class)
				.setParameter("tipoPessoa", tipoPessoa)
				.getSingleResult();
	}
	
	public Cliente buscarPorNome(String nome) {
		
		try {
			jpql = "from Cliente where upper(nome) like :nome";
			cliente = manager.createQuery(jpql, Cliente.class)
					.setParameter("nome", nome.toUpperCase())
					.getSingleResult();
			return cliente;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Cliente porDocumentoReceitaFederal(String documentoReceitaFederal) {
		
		try {
			jpql = "from Cliente where documentoReceitaFederal like :doc";
			cliente = manager.createQuery(jpql, Cliente.class)
					.setParameter("doc", documentoReceitaFederal)
					.getSingleResult();
			return cliente;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Cliente porTelefone(String telefone) {
		try {
			jpql = "from Cliente where telefone like :tel";
			cliente = manager.createQuery(jpql, Cliente.class)
					.setParameter("tel", telefone)
					.getSingleResult();
			return cliente;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Cliente porEmail(String email) {
		try {
			jpql = "from Cliente where lower(email) = :email";
			cliente = manager.createQuery(jpql, Cliente.class)
					.setParameter("email", email)
					.getSingleResult();
			
			return cliente;
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Cliente> filtrados(ClienteFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = builder.createQuery(Cliente.class);
		List<Predicate> predicates = new ArrayList<>();
		
		Root<Cliente> clienteRoot = criteriaQuery.from(Cliente.class);
		
		if (isNotBlank(filtro.getNome())) {
			predicates.add(builder.like(builder.lower(clienteRoot.get("nome")), 
					"%" + filtro.getNome().toLowerCase() + "%"));
		}
		
		if (isNotBlank(filtro.getDocumentoReceitaFederal())) {
			predicates.add(builder.like(builder.lower(clienteRoot.get("documentoReceitaFederal")), 
					"%" + filtro.getDocumentoReceitaFederal().toLowerCase() + "%"));
		}
		
		criteriaQuery.select(clienteRoot);
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		List<Order> orderList = new ArrayList<>(); 
		orderList.add(builder.asc(clienteRoot.get("nome")));
		criteriaQuery.orderBy(orderList);
		
		TypedQuery<Cliente> query = manager.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	@Transactional
	public void remover(Cliente cliente) {
		try {
			cliente = porId(cliente.getId());
			manager.remove(cliente);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O cliente " + cliente.getNome() + " não pode ser excluído. ");
		}
	}

}