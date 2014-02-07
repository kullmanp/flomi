package ch.kup.flomi.service;

import java.util.List;

import aQute.bnd.annotation.ProviderType;
import ch.kup.flomi.domain.Flomi;

@ProviderType
public interface FlomiBuchungService {

	/**
	 * Gets all years for which there exists a {@link Flomi}.
	 */
	List<Integer> getAllYears();

}
