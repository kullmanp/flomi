package ch.kup.flomi.integration.impl.test;

import org.junit.Test;

import ch.kup.flomi.domain.Address;
import ch.kup.flomi.integration.AddressRepository;

public class AddressRepositoryTest extends
		BaseTransactionTest<AddressRepository> {

	public AddressRepositoryTest() {
		super(AddressRepository.class);
	}

	@Test
	public void testFindAll() throws Exception {
		instance.findAll();
	}

	@Test
	public void testSaveNewAddress() throws Exception {
		Address address = new Address();
		Address address2 = instance.save(address);
		assertTrue(address2.getId() > 0);
	}
}
