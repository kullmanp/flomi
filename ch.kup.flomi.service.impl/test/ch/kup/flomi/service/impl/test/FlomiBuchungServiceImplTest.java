package ch.kup.flomi.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.kup.flomi.integration.FlomiRepository;
import ch.kup.flomi.service.impl.FlomiBuchungServiceImpl;

public class FlomiBuchungServiceImplTest {

	private FlomiBuchungServiceImpl cut;
	private FlomiRepository flomiRepository;

	@Before
	public void setup() {
		cut = new FlomiBuchungServiceImpl();
		flomiRepository = mock(FlomiRepository.class);
		cut.setFlomiRepository(flomiRepository);
	}

	@Test
	public void testGetAllYears() {
		when(flomiRepository.getAllYears()).thenReturn(
				Arrays.asList(new Integer[] { 12, 13 }));
		List<Integer> list = cut.getAllYears();
		assertEquals(2, list.size());
	}
}