package com.rafael.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.rafael.pedidovenda.model.Usuario;
import com.rafael.pedidovenda.repository.Usuarios;
import com.rafael.pedidovenda.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	@Transactional
	public Usuario salvar(Usuario usuario) {

		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
		
		// se as instâncias forem iguais, significa que pode ser guardada no DB, pois é uma edição do que existe
		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException(
					"Já existe um usuário com o email: " 
							+ usuario.getEmail() + " Informe um email diferente.");
		}

		return usuarios.guardar(usuario);
	}

}
