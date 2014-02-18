package ch.kup.flomi.integration.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.Address;
import ch.kup.flomi.integration.AddressRepository;

@Component
public class AddressRepositoryImpl extends BaseRepository<Address, Long>
		implements AddressRepository {

	@Override
	public List<Address> findByText(String searchtext) {
		TypedQuery<Address> query = entityManager.createQuery(
				"select a from Address a where a.lastName like :match",
				Address.class);
		query.setParameter("match", "%" + searchtext.trim().toLowerCase() + "%");
		List<Address> resultList = query.getResultList();
		System.out.println("SEARCH RESULTS: " + resultList.size());
		return resultList;
	}

	@Reference
	public void setEM(EntityManager em) {
		this.entityManager = em;
	}

}
