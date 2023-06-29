package com.rafael.pedidovenda.repository;

import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.ilike;
import static org.hibernate.criterion.Restrictions.ge;
import static org.hibernate.criterion.Restrictions.le;
import static org.hibernate.criterion.Restrictions.in;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import static org.hibernate.criterion.MatchMode.ANYWHERE;

import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.model.Usuario;
import com.rafael.pedidovenda.model.vo.DataValor;
import com.rafael.pedidovenda.repository.filter.PedidoFilter;

public class Pedidos implements Serializable {

	private static final String DATA_CRIACAO = "dataCriacao";

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
			criteria.add(ge(DATA_CRIACAO, filtro.getDataCriacaoDe()));
		}

		if (filtro.getDataCriacaoAte() != null) {
			criteria.add(le(DATA_CRIACAO, filtro.getDataCriacaoAte()));
		}

		if (isNotBlank(filtro.getNomeCliente())) {
			criteria.add(ilike("c.nome", filtro.getNomeCliente(), ANYWHERE));
		}

		if (isNotBlank(filtro.getNomeVendedor())) {
			criteria.add(ilike("v.nome", filtro.getNomeVendedor(), ANYWHERE));
		}

		if (filtro.getStatuses() != null && filtro.getStatuses().length > 0) {
			criteria.add(in("status", (Object[]) filtro.getStatuses()));
		}

		return criteria.addOrder(asc("id")).list();
	}

	public Pedido porId(Long id) {
		return manager.find(Pedido.class, id);
	}

	public Pedido guardar(Pedido pedido) {
		return manager.merge(pedido);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Map<Date, BigDecimal> valoresTotaisPorData(Integer numeroDeDias, Usuario criadoPor) {
		Session session = manager.unwrap(Session.class);
		
		numeroDeDias -=1;
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
		dataInicial.add(Calendar.DAY_OF_MONTH, numeroDeDias * -1);
		
		Map<Date, BigDecimal> resultado = criarMapaVazio(numeroDeDias, dataInicial);
		
		Criteria criteria = session.createCriteria(Pedido.class);
		
		criteria.setProjection(Projections.projectionList()
				.add(Projections.sqlGroupProjection("date(data_criacao) as data", 
						"date(data_criacao)", new String[] { "data" }, 
						new Type[] { StandardBasicTypes.DATE } ))
				.add(Projections.sum("valorTotal").as("valor"))
			)
			.add(Restrictions.ge(DATA_CRIACAO, dataInicial.getTime()));
		
		if(criadoPor != null) {
			criteria.add(Restrictions.eq("vendedor", criadoPor));
		}
		
		List<DataValor> valoresPorData = criteria
				.setResultTransformer(Transformers.aliasToBean(DataValor.class)).list();
		
		for(DataValor dataValor : valoresPorData) {
			resultado.put(dataValor.getData(), dataValor.getValor());
		}
		
		return resultado;
	}

	private Map<Date, BigDecimal> criarMapaVazio(Integer numeroDeDias, Calendar dataInicial) {
		dataInicial = (Calendar) dataInicial.clone();
		Map<Date, BigDecimal> mapaInicial = new TreeMap<>();
		
		for (int i = 0; i <= numeroDeDias; i++) {
			mapaInicial.put(dataInicial.getTime(), BigDecimal.ZERO);
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return mapaInicial;
	}

}