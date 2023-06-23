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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.rafael.pedidovenda.model.Grupo;
import com.rafael.pedidovenda.model.Usuario;
import com.rafael.pedidovenda.repository.filter.UsuarioFilter;
import com.rafael.pedidovenda.service.NegocioException;
import com.rafael.pedidovenda.util.jpa.Transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	private String jpql;
	
	public List<Usuario> vendedores() {
		jpql = "from Usuario";
		return manager.createQuery(jpql, Usuario.class)
				.getResultList();
	}

	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}
	
	public Usuario porEmail(String email) {
		try {
			jpql = "from Usuario where lower(email) = :email";
			return manager.createQuery(jpql, Usuario.class)
					.setParameter("email", email.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			// Nenhum usuário encontrado com o e-mail informado.
			return null;
		}
	}
	
	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}

	@Transactional
	public void remover(Usuario usuario) {
		try {
			usuario = porId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O Usuário " + usuario.getNome() + " não pode ser excluído. ");
		}
		
	}

	public List<Grupo> grupos() {
		jpql = "from Grupo";
		return manager.createQuery(jpql, Grupo.class)
				.getResultList();
	}
	
	public List<Usuario> filtrados(UsuarioFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
		List<Predicate> predicates = new ArrayList<>();
		
		Root<Usuario> usuarioRoot = criteriaQuery.from(Usuario.class);	
								
		if (isNotBlank(filtro.getNome())) {
			predicates.add(builder.like(builder.lower(usuarioRoot.get("nome")), 
					"%" + filtro.getNome().toLowerCase() + "%"));
		}
		
		criteriaQuery.select(usuarioRoot);
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		if(filtro.getGrupo() != null) {
			Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
		    Root<Usuario> subroot = subquery.from(Usuario.class);
		    subquery.select(subroot.<Long>get("id"));
		    Join<Usuario, Grupo> grupos = subroot.join("grupos");
		    subquery.where(builder.equal(grupos.get("id"), filtro.getGrupo().getId()));
		    criteriaQuery.where(builder.in(usuarioRoot.get("id")).value(subquery));
		}
		
		List<Order> orderList = new ArrayList<>(); 
		orderList.add(builder.asc(usuarioRoot.get("nome")));
		criteriaQuery.orderBy(orderList);
		
		TypedQuery<Usuario> query = manager.createQuery(criteriaQuery);
		return query.getResultList();
	}
}
