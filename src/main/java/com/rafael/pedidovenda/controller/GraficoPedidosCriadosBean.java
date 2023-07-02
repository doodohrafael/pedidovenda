package com.rafael.pedidovenda.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import com.rafael.pedidovenda.model.Usuario;
import com.rafael.pedidovenda.repository.Pedidos;
import com.rafael.pedidovenda.security.UsuarioLogado;
import com.rafael.pedidovenda.security.UsuarioSistema;

import lombok.Getter;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean implements Serializable {

	private static final int DIAS_PEDIDOS_CRIADOS = 15;

	private static final long serialVersionUID = 1L;
	
	private DateFormat formatadorDeData = new SimpleDateFormat("dd/MM");
	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	@Getter
	private LineChartModel model;
	
	public void preRender() {
		model = new LineChartModel();
		model.setTitle("Pedidos criados dos últimos 15 dias");
		model.setLegendPosition("e");
		model.setAnimate(true);
		model.setDatatipFormat("R$%.0s%s");
		
		model.getAxes().put(AxisType.X, new CategoryAxis());
		
		adicionarSeries("Todos os pedidos", null);
		adicionarSeries("Meus pedidos", usuarioLogado.getUsuario());
	}
	
	private void adicionarSeries(String rotulo, Usuario criadoPor) {
		Map<Date, BigDecimal> valoresPorData = pedidos.valoresTotaisPorData(DIAS_PEDIDOS_CRIADOS, criadoPor);
		
		ChartSeries series = new ChartSeries(rotulo);
		
		for (Date data : valoresPorData.keySet()) {
			series.set(formatadorDeData.format(data), valoresPorData.get(data));
		}
		
		model.addSeries(series);
	}

}
