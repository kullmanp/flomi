package ch.kup.flomi.integration.impl.test;

import javax.transaction.TransactionManager;

import org.amdatu.bndtools.test.BaseOSGiServiceTest;
import org.junit.Test;

import ch.kup.flomi.domain.Address;
import ch.kup.flomi.integration.AddressRepository;

public class AddressRepositoryTest extends
		BaseOSGiServiceTest<AddressRepository> {

	private TransactionManager transactionManager;

	public AddressRepositoryTest() {
		super(AddressRepository.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		transactionManager = getService(TransactionManager.class);
		transactionManager.begin();
	}

	@Override
	protected void tearDown() throws Exception {
		transactionManager.rollback();
		super.tearDown();
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
