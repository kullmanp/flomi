package ch.kup.flomi.integration.impl.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import ch.kup.flomi.domain.Flomi;
import ch.kup.flomi.domain.FlomiBuchung;
import ch.kup.flomi.integration.FlomiBuchungRepository;
import ch.kup.flomi.integration.FlomiRepository;

public class FlomiBuchungRepositoryTest extends
		BaseTransactionTest<FlomiBuchungRepository> {

	private final FlomiRepository flomiRepository;

	public FlomiBuchungRepositoryTest() {
		super(FlomiBuchungRepository.class);
		flomiRepository = getService(FlomiRepository.class);
	}

	@Test
	public void testFindByYear() {
		Flomi f = new Flomi();
		f.setDate(new GregorianCalendar(2008, Calendar.JANUARY, 1).getTime());
		flomiRepository.save(f);

		FlomiBuchung fb = new FlomiBuchung();
		fb.setFlomi(f);
		instance.save(fb);

		assertEquals(0, instance.findReservationsByYear(2007).size());
		assertEquals(1, instance.findReservationsByYear(2008).size());
	}
}
