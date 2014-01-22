package ch.kup.flomi.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.Address;
import ch.kup.flomi.integration.AddressRepository;

@Path("address")
@Component(provide = Object.class)
public class AddressResource {
	private AddressRepository addressRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Address> getAddresses() {
		return getAddressRepository().findAll();
	}

	public AddressRepository getAddressRepository() {
		return addressRepository;
	}

	@Reference
	public void setAddressRepository(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

}
