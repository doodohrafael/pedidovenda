package com.rafael.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.rafael.pedidovenda.model.Cliente;
import com.rafael.pedidovenda.repository.Clientes;
import static com.rafael.pedidovenda.util.cdi.CDIServiceLocator.getBean;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter<Cliente> {
	
	private Clientes clientes;
	
	public ClienteConverter() {
		clientes = getBean(Clientes.class);
	}
	
	@Override
	public Cliente getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente cliente = new Cliente();
		
		if(isNotBlank(value)) {
			Long valueId = Long.parseLong(value);
			cliente = clientes.porId(valueId);
		}
		return cliente;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Cliente value) {
		if (value != null) {
			Cliente cliente = (Cliente) value;
			return cliente.getId() == null ? null : cliente.getId().toString();
			
		}
		return "";
	}

}
