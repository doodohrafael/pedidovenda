package com.rafael.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.rafael.pedidovenda.model.Usuario;
import com.rafael.pedidovenda.repository.Usuarios;
import static com.rafael.pedidovenda.util.cdi.CDIServiceLocator.getBean;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter<Usuario> {
	
	private Usuarios usuarios;
	
	public UsuarioConverter() {
		usuarios = getBean(Usuarios.class);
	}
	
	@Override
	public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario usuario = new Usuario();
		
		if(isNotBlank(value)) {
			Long valueId = Long.parseLong(value);
			usuario = usuarios.porId(valueId);
		}
		return usuario;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Usuario value) {
		if (value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
			
		}
		return "";
	}

}
