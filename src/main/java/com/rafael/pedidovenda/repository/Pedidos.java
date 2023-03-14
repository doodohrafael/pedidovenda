package com.rafael.pedidovenda.repository;

import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.ilike;
import static org.hibernate.criterion.Restrictions.ge;
import static org.hibernate.criterion.Restrictions.le;
import static org.hibernate.criterion.Restrictions.in;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import static org.hibernate.criterion.MatchMode.ANYWHERE;

import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.repository.filter.PedidoFilter;
import com.rafael.pedidovenda.service.NegocioException;

public class Pedidos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Pedido> filtrados(PedidoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pedido.class)
				.createAlias("cliente", "c")
				.createAlias("vendedor", "v");

		if (filtro.getNumeroDe() != null) {
			criteria.add(ge("id", filtro.getNumeroDe()));
		}

		if (filtro.getNumeroAte() != null) {
			criteria.add(le("id", filtro.getNumeroAte()));
		}

		if (filtro.getDataCriacaoDe() != null) {
			criteria.add(ge("dataCriacao", filtro.getDataCriacaoDe()));
		}

		if (filtro.getDataCriacaoAte() != null) {
			criteria.add(le("dataCriacao", filtro.getDataCriacaoAte()));
		}

		if (isNotBlank(filtro.getNomeCliente())) {
			criteria.add(ilike("c.nome", filtro.getNomeCliente(), ANYWHERE));
		}

		if (isNotBlank(filtro.getNomeVendedor())) {
			criteria.add(ilike("v.nome", filtro.getNomeVendedor(), ANYWHERE));
		}

		if (filtro.getStatuses() != null && filtro.getStatuses().length > 0) {
			criteria.add(in("status", filtro.getStatuses()));
		}

		return criteria.addOrder(asc("id")).list();
	}

	public Pedido porId(Long id) {
		return manager.find(Pedido.class, id);
	}

}
