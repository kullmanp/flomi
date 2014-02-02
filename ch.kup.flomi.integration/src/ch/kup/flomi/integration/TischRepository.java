package ch.kup.flomi.integration;

import aQute.bnd.annotation.ProviderType;
import ch.kup.flomi.domain.Tisch;

@ProviderType
public interface TischRepository extends Repository<Tisch, Long> {

	Tisch findByName(String name);
}
