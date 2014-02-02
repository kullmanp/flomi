package ch.kup.flomi.integration.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.Tisch;
import ch.kup.flomi.integration.TischRepository;

@Component
public class TischRepositoryImpl extends BaseRepository<Tisch, Long> implements
		TischRepository {

	@Override
	public Tisch findByName(String name) {
		TypedQuery<Tisch> query = entityManager.createQuery(
				"select t from Tisch t where t.name = :name", Tisch.class);
		query.setParameter("name", name);
		List<Tisch> list = query.getResultList();
		return list.size() == 0 ? null : list.get(0);
	}

	@Reference
	public void setEM(EntityManager em) {
		this.entityManager = em;
	}

}
