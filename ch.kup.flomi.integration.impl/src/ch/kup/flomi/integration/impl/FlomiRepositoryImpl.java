package ch.kup.flomi.integration.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import aQute.bnd.annotation.component.Component;
import ch.kup.flomi.domain.Flomi;
import ch.kup.flomi.integration.FlomiRepository;

@Component
public class FlomiRepositoryImpl extends BaseRepository implements FlomiRepository {


	@PersistenceContext
	private EntityManager em;

	public void activate() {
		System.out.println("HELLO. I'm the repository. My emf is " + emf);
	}
	
	@Override
	public List<Flomi> findAll() {
		return em.createQuery("select f from Flomi f", Flomi.class)
				.getResultList();
	}


}
