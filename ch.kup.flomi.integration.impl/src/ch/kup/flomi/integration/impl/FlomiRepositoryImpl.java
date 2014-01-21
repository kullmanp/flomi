package ch.kup.flomi.integration.impl;

import javax.persistence.EntityManager;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.Flomi;
import ch.kup.flomi.integration.FlomiRepository;

@Component
public class FlomiRepositoryImpl extends BaseRepository<Flomi, Long> implements FlomiRepository {

	@Reference
	public void setEM(EntityManager em) {
		this.entityManager = em;
	}

}
