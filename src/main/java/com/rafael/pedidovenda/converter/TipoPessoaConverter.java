package com.rafael.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.rafael.pedidovenda.model.Cliente;
import com.rafael.pedidovenda.model.TipoPessoa;
import com.rafael.pedidovenda.repository.Clientes;
import static com.rafael.pedidovenda.util.cdi.CDIServiceLocator.getBean;

@FacesConverter(forClass = Cliente.class, value = "tipoPessoaConverter")
public class TipoPessoaConverter implements Converter<TipoPessoa> {
	
	private Clientes clientes;
	
	public TipoPessoaConverter() {
		clientes = getBean(Clientes.class);
	}

	@Override
	public TipoPessoa getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente cliente = new Cliente();
		
		if(isNotBlank(value)) {
			if(value.equals(TipoPessoa.FISICA.getDescricao())) {
				cliente = clientes.porTipoPessoa(TipoPessoa.FISICA);
			} 
		}
		return cliente.getTipo();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, TipoPessoa value) {
		if (value != null) {
			TipoPessoa tipoPessoa = (TipoPessoa) value;
			return tipoPessoa.getDescricao() == null ? null : tipoPessoa.getDescricao();
			
		}
		return "";
	}
	

}
