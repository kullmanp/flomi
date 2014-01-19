package ch.kup.flomi.integration;


import java.util.List;

import ch.kup.flomi.domain.Flomi;
import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface FlomiRepository {
	List<Flomi> findAll();

	Flomi save(Flomi flomi);
}
