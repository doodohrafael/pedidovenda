package com.rafael.pedidovenda.converter;

import static com.rafael.pedidovenda.util.cdi.CDIServiceLocator.getBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.rafael.pedidovenda.model.Produto;
import com.rafael.pedidovenda.repository.Produtos;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter<Produto> {

	private Produtos produtos;

	public ProdutoConverter() {
		produtos = getBean(Produtos.class);
	}

	@Override
	public Produto getAsObject(FacesContext context, UIComponent component, String value) {
		Produto produto = new Produto();

		if (isNotBlank(value)) {
			Long valueId = Long.parseLong(value);
			produto = produtos.porId(valueId);
		}
		return produto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Produto value) {
		if (value != null) {
			Produto produto = (Produto) value;
			return produto.getId() == null ? null : produto.getId().toString();
		}
		return "";
	}

}
