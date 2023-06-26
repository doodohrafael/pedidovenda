package com.rafael.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.elements.Elements;
import org.primefaces.model.charts.optionconfig.elements.ElementsLine;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;

import lombok.Getter;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private LineChartModel model;
	
	public void preRender() {
		createLineModel();
	}

	public void createLineModel() {
        model = new LineChartModel();
        ChartData data = new ChartData();
        
        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        values.add(Math.random() * 1000);
        values.add(Math.random() * 1000);
        values.add(Math.random() * 1000);
        values.add(Math.random() * 1000);
        values.add(Math.random() * 1000);
        
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Todos os pedidos");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setBackgroundColor("rgb(75, 192, 192)");

        LineChartDataSet dataSet2 = new LineChartDataSet();
        List<Object> values2 = new ArrayList<>();
        values2.add(Math.random() * 1000);
        values2.add(Math.random() * 1000);
        values2.add(Math.random() * 1000);
        values2.add(Math.random() * 1000);
        values2.add(Math.random() * 1000);
        
        dataSet2.setData(values2);
        dataSet2.setFill(false);
        dataSet2.setLabel("Meus pedidos");
        dataSet2.setBorderColor("rgb(246, 184, 0)");
        dataSet2.setBackgroundColor("rgb(246, 184, 0)");
        
        data.addChartDataSet(dataSet);
        data.addChartDataSet(dataSet2);

        List<String> labels = new ArrayList<>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        data.setLabels(labels);

        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Pedidos criados");
        options.setTitle(title);
        
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("right");
        options.setLegend(legend);
        
        model.setOptions(options);
        model.setData(data);
    }

}
