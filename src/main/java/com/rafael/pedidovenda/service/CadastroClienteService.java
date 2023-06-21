package com.rafael.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.rafael.pedidovenda.model.Cliente;
import com.rafael.pedidovenda.repository.Clientes;
import com.rafael.pedidovenda.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;
	
	private Cliente clienteExistente;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {

		verificarPorNome(cliente);

		verificarPorDocumentoReceitaFederal(cliente);

		verificarPorTelefone(cliente);

		verificarPorEmail(cliente);

		return clientes.guardar(cliente);
	}

	private void verificarPorEmail(Cliente cliente) {
		clienteExistente = clientes.porEmail(cliente.getEmail());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente com o email " 
				+ cliente.getEmail() + " informado");
		}
	}

	private void verificarPorNome(Cliente cliente) {
		clienteExistente = clientes.buscarPorNome(cliente.getNome());

		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente com o nome " 
				+ cliente.getNome() + " informado.");
		}
	}
	
	private void verificarPorDocumentoReceitaFederal(Cliente cliente) {
		clienteExistente = clientes
				.porDocumentoReceitaFederal(cliente.getDocumentoReceitaFederal());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException(
					"Já existe um cliente com o documento " + cliente.getDocumentoReceitaFederal() 
					+ " informado.");
		}
	}
	
	private void verificarPorTelefone(Cliente cliente) {
		clienteExistente = clientes.porTelefone(cliente.getTelefone());
				
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente com o telefone " 
						+ cliente.getTelefone() + " informado");
		}
	}
	
}