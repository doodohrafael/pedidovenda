package com.rafael.pedidovenda;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.rafael.pedidovenda.model.Cliente;
import com.rafael.pedidovenda.model.Endereco;

import static com.rafael.pedidovenda.model.TipoPessoa.FISICA;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class Teste {

	public static void main(String[] args) {

//		EntityManagerFactory factory = createEntityManagerFactory("PedidoPU");
//		EntityManager manager = factory.createEntityManager();
//
//		EntityTransaction trx = manager.getTransaction();
//		trx.begin();

//		Cliente cliente = new Cliente();
//		cliente.setNome("Douglas Rafael");
//		cliente.setEmail("doodohrafael@gmail.com");
//		cliente.setDocumentoReceitaFederal("107.691.566-63");
//		cliente.setTipo(FISICA);
//		
//		Endereco endereco = new Endereco();
//		endereco.setLogradouro("Rua quipapá");
//		endereco.setNumero("294");
//		endereco.setCidade("Paulista");
//		endereco.setCep("53435-070");
//		endereco.setUf("PE");
//		endereco.setComplemento("Privê - Casa 1");
//		
//		endereco.setCliente(cliente);
//		
//		cliente.getEnderecos().add(endereco);
//		
//		manager.persist(cliente);
		
//		trx.commit();

	}

}
