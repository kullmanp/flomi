package ch.kup.flomi.integration.impl.test;

import javax.transaction.TransactionManager;

import org.amdatu.bndtools.test.BaseOSGiServiceTest;
import org.junit.Test;

import ch.kup.flomi.integration.FlomiRepository;

public class FlomiRepositoryTest extends BaseOSGiServiceTest<FlomiRepository> {

	private TransactionManager transactionManager;

	public FlomiRepositoryTest() {
		super(FlomiRepository.class);
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
}
