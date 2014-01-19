package ch.kup.flomi.integration.impl;

import java.util.List;

import javax.persistence.EntityManager;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.Flomi;
import ch.kup.flomi.integration.FlomiRepository;

@Component
public class FlomiRepositoryImpl implements FlomiRepository {

	private EntityManager em;

	public void activate() {
		System.out.println("HELLO. I'm the repository. My em is " + em);
	}

	@Override
	public List<Flomi> findAll() {
		return em.createQuery("select f from Flomi f", Flomi.class)
				.getResultList();
	}

	@Reference
	public void setEM(EntityManager em) {
		this.em = em;
	}

	@Override
	public Flomi save(Flomi flomi) {
		if (flomi.getId() == null) {
			em.persist(flomi);
			return flomi;
		} else {
			return em.merge(flomi);
		}
	}

}
