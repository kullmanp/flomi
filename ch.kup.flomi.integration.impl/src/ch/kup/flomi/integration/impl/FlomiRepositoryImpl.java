package ch.kup.flomi.integration.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.Flomi;
import ch.kup.flomi.integration.FlomiRepository;

@Component
public class FlomiRepositoryImpl extends BaseRepository<Flomi, Long> implements
		FlomiRepository {

	@Override
	public Flomi findByName(String name) {
		TypedQuery<Flomi> query = entityManager.createQuery(
				"select f from Flomi f where f.name = :name", Flomi.class);
		query.setParameter("name", name);
		List<Flomi> list = query.getResultList();
		return list.size() == 0 ? null : list.get(0);
	}

	@Reference
	public void setEM(EntityManager em) {
		this.entityManager = em;
	}

}
