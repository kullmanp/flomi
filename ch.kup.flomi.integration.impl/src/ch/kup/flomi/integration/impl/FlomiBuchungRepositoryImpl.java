package ch.kup.flomi.integration.impl;

import javax.persistence.EntityManager;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.FlomiBuchung;
import ch.kup.flomi.integration.FlomiBuchungRepository;

@Component
public class FlomiBuchungRepositoryImpl extends
		BaseRepository<FlomiBuchung, Long> implements FlomiBuchungRepository {

	@Reference
	public void setEM(EntityManager em) {
		this.entityManager = em;
	}

}
