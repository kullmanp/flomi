package ch.kup.flomi.integration;

import java.util.List;

import aQute.bnd.annotation.ProviderType;
import ch.kup.flomi.domain.Flomi;

@ProviderType
public interface FlomiRepository extends Repository<Flomi, Long> {

	Flomi findByName(String name);

	List<Flomi> findByYear(int year);

	List<Integer> getAllYears();
}
