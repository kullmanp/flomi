package ch.kup.flomi.integration.impl;

import static org.mockito.Mockito.mock;

import javax.persistence.EntityManager;

import junit.framework.TestCase;
import ch.kup.flomi.domain.Address;

public class AddressRepositoryImplTest extends TestCase {
	private AddressRepositoryImpl cut;
	private EntityManager em;

	@Override
	public void setUp() {
		em = mock(EntityManager.class);
		cut = new AddressRepositoryImpl();
		cut.setEM(em);
	}

	public void testSave() {
		Address entity = new Address();
		cut.save(entity);
	}
}
