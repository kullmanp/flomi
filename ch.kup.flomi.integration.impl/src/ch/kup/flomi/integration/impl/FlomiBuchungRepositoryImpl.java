package ch.kup.flomi.integration.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.FlomiBuchung;
import ch.kup.flomi.integration.FlomiBuchungRepository;

@Component
public class FlomiBuchungRepositoryImpl extends
		BaseRepository<FlomiBuchung, Long> implements FlomiBuchungRepository {

	@Override
	public List<FlomiBuchung> findReservationsByYear(int year) {
		TypedQuery<FlomiBuchung> query = entityManager
				.createQuery(
						"select b from FlomiBuchung b where sql('extract(year from ?)', b.flomi.date) = :year",
						FlomiBuchung.class);
		query.setParameter("year", year);
		return query.getResultList();
	}

	@Reference
	public void setEM(EntityManager em) {
		this.entityManager = em;
	}

}
