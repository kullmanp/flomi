package ch.kup.flomi.integration.impl.test;

import javax.persistence.EntityManager;

import org.amdatu.bndtools.test.BaseOSGiServiceTest;

public abstract class BaseTransactionTest<S> extends BaseOSGiServiceTest<S> {
	private EntityManager entityManager;

	public BaseTransactionTest(Class<S> clazz) {
		super(clazz);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		entityManager = getService(EntityManager.class);
		assertFalse(entityManager.getTransaction().isActive());
		entityManager.getTransaction().begin();
	}

	@Override
	protected void tearDown() throws Exception {
		/*
		 * flush so that we can see errors from the db. (If we rollback without
		 * flush then we never touch the db)
		 */
		entityManager.flush();
		entityManager.getTransaction().rollback();
		entityManager.close();
		super.tearDown();
	}

}
