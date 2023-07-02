package com.rafael.pedidovenda.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;

import com.rafael.pedidovenda.util.jsf.FacesUtil;
import com.rafael.pedidovenda.util.report.ExecutorRelatorio;

import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class RelatorioPedidosEmitidosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@NotNull
	private Date dataInicio;
	
	@Getter
	@Setter
	@NotNull
	private Date dataFim;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletResponse response;
	
	@Inject
	private EntityManager manager;
	
	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorio_pedidos_emitidos.jasper",
				response, parametros, "Pedidos emitodos.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}
	
}



