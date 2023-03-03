package com.rafael.pedidovenda.converter;

import static com.rafael.pedidovenda.util.cdi.CDIServiceLocator.getBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

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

		if (StringUtils.isNotBlank(value)) {
			Long valueId = Long.parseLong(value);
			produto = produtos.porId(valueId);
		}
		return produto;
	}

	@Override // Erro do botão no esta aqui. O value vem null.
	public String getAsString(FacesContext context, UIComponent component, Produto value) {
		if (value != null) {
			return ((Produto) value).getId().toString();
		}
		return "";
	}

}
