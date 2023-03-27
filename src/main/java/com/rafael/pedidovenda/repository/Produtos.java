package com.rafael.pedidovenda.repository;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.hibernate.criterion.MatchMode.ANYWHERE;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.ilike;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.rafael.pedidovenda.model.Produto;
import com.rafael.pedidovenda.repository.filter.ProdutoFilter;
import com.rafael.pedidovenda.service.NegocioException;
import com.rafael.pedidovenda.util.jpa.Transactional;

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	private String jpql;

	public Produto guardar(Produto produto) {
		return produto = manager.merge(produto);
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
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Produto> filtrados(ProdutoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		
		if(isNotBlank(filtro.getSku())) {
			criteria.add(eq("sku", filtro.getSku()));
		}
		
		if(isNotBlank(filtro.getNome())) {
			criteria.add(ilike("nome", filtro.getNome(), ANYWHERE));
		}
		
		return criteria.addOrder(asc("nome")).list();
	}

	public Produto porId(Long id) {
		return manager.find(Produto.class, id);
	}

	@Transactional
	public void remover(Produto produto) {
		try {
			produto = porId(produto.getId());
			manager.remove(produto);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Produto não pode ser excluído.");
		}
	}

}
