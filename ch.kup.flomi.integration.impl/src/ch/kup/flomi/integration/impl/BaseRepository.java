package ch.kup.flomi.integration.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import aQute.bnd.annotation.component.Reference;

public class BaseRepository {
	protected EntityManagerFactory emf;

	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	@Reference
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}
}
