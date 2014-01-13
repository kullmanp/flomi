package org.example;

import javax.persistence.EntityManagerFactory;

import aQute.bnd.annotation.component.*;

@Component
public class ExampleComponent {

	private EntityManagerFactory emf;

	public EntityManagerFactory getEmf() {
		return emf;
	}

	@Reference
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	 
	public void activate() {
		System.out.println("HALLO");
	}

}