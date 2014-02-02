package ch.kup.flomi.integration.impl.test;

import javax.transaction.TransactionManager;

import org.amdatu.bndtools.test.BaseOSGiServiceTest;
import org.junit.Test;

import ch.kup.flomi.integration.TischRepository;

public class TischRepositoryTest extends BaseOSGiServiceTest<TischRepository> {

	private TransactionManager transactionManager;

	public TischRepositoryTest() {
		super(TischRepository.class);
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
	public void testFindByName() throws Exception {
		instance.findByName("test");
	}
}
