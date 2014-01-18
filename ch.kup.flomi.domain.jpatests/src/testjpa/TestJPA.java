package testjpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.Flomi;

@Component
public class TestJPA {

	private volatile EntityManagerFactory emf;

	@Reference
	void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void activate() throws Exception {
		try {
			exampleCreate();
			example();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void exampleCreate() {
		Flomi f = new Flomi();
		f.setName("My first Flomi");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();
	}
	
	private void example() {
		System.out.println("TEST JPA :" + emf);

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Flomi> query = em.createQuery("select f from Flomi f", Flomi.class);
		List<Flomi> resultList = query.getResultList();
		for (Flomi flomi : resultList) {
			System.out.println(flomi);
		}
		em.getTransaction().commit();
		System.out.println("Done");
		em.close();
	}
}
