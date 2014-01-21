package ch.kup.flomi.integration.impl;

import javax.persistence.EntityManager;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.Address;
import ch.kup.flomi.integration.AddressRepository;

@Component
public class AddressRepositoryImpl extends BaseRepository<Address, Long>
		implements AddressRepository {

	@Reference
	public void setEM(EntityManager em) {
		this.entityManager = em;
	}

}
