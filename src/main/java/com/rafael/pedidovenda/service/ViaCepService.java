package com.rafael.pedidovenda.service;

import java.io.IOException;
import java.io.Serializable;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.rafael.pedidovenda.model.EnderecoViaCep;

public class ViaCepService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("null")
	public EnderecoViaCep buscarEnderecoPorCep(String cep) {
		EnderecoViaCep endereco = null;
		
		HttpGet request = new HttpGet("https://viacep.com.br/ws/" + cep + "/json/");
		String result = null;

		try(CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
				CloseableHttpResponse response = httpClient.execute(request)) {
			
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				result = EntityUtils.toString(entity);
				
				Gson gson = new Gson();
				endereco = gson.fromJson(result, EnderecoViaCep.class);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			
		if (result.contains("\"erro\": true")) {
			endereco = null;
			throw new NegocioException("O cep informado não existe!");
		}
		
		return endereco;
	}

}
