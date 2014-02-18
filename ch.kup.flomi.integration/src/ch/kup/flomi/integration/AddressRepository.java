package ch.kup.flomi.integration;

import java.util.List;

import aQute.bnd.annotation.ProviderType;
import ch.kup.flomi.domain.Address;

@ProviderType
public interface AddressRepository extends Repository<Address, Long> {

	List<Address> findByText(String searchtext);
}
