package com.rafael.pedidovenda.converter;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.rafael.pedidovenda.model.Grupo;
import com.rafael.pedidovenda.repository.Grupos;

@FacesConverter(forClass = Grupo.class, value = "grupoConverter")
public class GrupoConverter implements Converter<Grupo> {
	
	@Inject
	private Grupos grupos;

	@Override
	public Grupo getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = new Grupo();
		
		if(isNotBlank(value)) {
			Long valueId = Long.parseLong(value);
			retorno = grupos.porId(valueId);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Grupo value) {
		if (value != null) {
			Grupo grupo = (Grupo) value;
			return grupo.getId() == null ? null : grupo.getId().toString();
		}
		return "";
	}

}
