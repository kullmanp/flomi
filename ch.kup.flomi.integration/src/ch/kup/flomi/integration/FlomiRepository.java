package ch.kup.flomi.integration;


import aQute.bnd.annotation.ProviderType;
import ch.kup.flomi.domain.Flomi;

@ProviderType
public interface FlomiRepository extends Repository<Flomi, Long>{
}
