package ch.kup.flomi.integration.impl.test;

import org.junit.Test;

import ch.kup.flomi.integration.TischRepository;

public class TischRepositoryTest extends BaseTransactionTest<TischRepository> {

	public TischRepositoryTest() {
		super(TischRepository.class);
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
