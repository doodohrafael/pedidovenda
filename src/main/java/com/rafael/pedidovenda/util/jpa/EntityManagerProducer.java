package com.rafael.pedidovenda.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;

import static javax.persistence.Persistence.createEntityManagerFactory;

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;
	
	public EntityManagerProducer() {
		factory = createEntityManagerFactory("PedidoPU");
	}
	
	@Produces @RequestScoped
	public Session createEntityManager() {
		return (Session) factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes Session manager) {
		manager.close();
	}
}
