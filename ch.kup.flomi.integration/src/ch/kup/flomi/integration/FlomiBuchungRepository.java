package ch.kup.flomi.integration;

import java.util.List;

import aQute.bnd.annotation.ProviderType;
import ch.kup.flomi.domain.FlomiBuchung;

@ProviderType
public interface FlomiBuchungRepository extends Repository<FlomiBuchung, Long> {

	List<FlomiBuchung> findReservationsByYear(int year);
}
