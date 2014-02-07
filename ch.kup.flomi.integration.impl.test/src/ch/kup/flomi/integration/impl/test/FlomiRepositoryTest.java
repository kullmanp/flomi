package ch.kup.flomi.integration.impl.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import ch.kup.flomi.domain.Flomi;
import ch.kup.flomi.integration.FlomiRepository;

public class FlomiRepositoryTest extends BaseTransactionTest<FlomiRepository> {

	public FlomiRepositoryTest() {
		super(FlomiRepository.class);
	}

	@Test
	public void testFindAll() throws Exception {
		instance.findAll();
	}

	@Test
	public void testFindByName() throws Exception {
		instance.findByName("test");
	}

	@Test
	public void testGetAllYears() throws Exception {
		List<Integer> years = instance.getAllYears();
		assertEquals(0, years.size());

		Flomi flomi1 = new Flomi();
		flomi1.setDate(new GregorianCalendar(2011, Calendar.JANUARY, 1)
				.getTime());
		instance.save(flomi1);

		years = instance.getAllYears();
		assertEquals(1, years.size());
		assertEquals(2011, years.get(0).intValue());

		Flomi flomi2 = new Flomi();
		flomi2.setDate(new GregorianCalendar(2012, Calendar.JANUARY, 1)
				.getTime());
		instance.save(flomi2);

		years = instance.getAllYears();
		assertEquals(2, years.size());

		// another one of the same year
		Flomi flomi3 = new Flomi();
		flomi3.setDate(new GregorianCalendar(2012, Calendar.JANUARY, 1)
				.getTime());
		instance.save(flomi3);

		years = instance.getAllYears();
		assertEquals(2, years.size());
	}
}
