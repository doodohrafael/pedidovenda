package com.rafael.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.rafael.pedidovenda.model.Categoria;
import com.rafael.pedidovenda.repository.Categorias;
import static com.rafael.pedidovenda.util.cdi.CDIServiceLocator.getBean;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter<Categoria> {

	private Categorias categorias;

	public CategoriaConverter() {
		categorias = getBean(Categorias.class);
	}

	@Override
	public Categoria getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria categoria = null;

		if (value != null) {
			Long valueId = Long.parseLong(value);
			categoria = categorias.porId(valueId);
		}
		return categoria;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Categoria value) {
		if (value != null) {
			return ((Categoria) value).getId().toString();
		}
		return "";
	}

}
