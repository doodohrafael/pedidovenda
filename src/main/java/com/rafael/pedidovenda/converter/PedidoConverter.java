package com.rafael.pedidovenda.converter;

import static com.rafael.pedidovenda.util.cdi.CDIServiceLocator.getBean;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.repository.Pedidos;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter<Pedido> {
	
	private Pedidos pedidos;
	
	public PedidoConverter() {
		pedidos = getBean(Pedidos.class);
	}
	
	@Override
	public Pedido getAsObject(FacesContext context, UIComponent component, String value) {
		Pedido pedido = new Pedido();
		
		if(isNotBlank(value)) {
			Long valueId = Long.parseLong(value);
			pedido = pedidos.porId(valueId);
		}
		return pedido;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Pedido value) {
		if (value != null) {
			Pedido pedido = (Pedido) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
			
		}
		return "";
	}

}
