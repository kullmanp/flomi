package ch.kup.flomi.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.Address;
import ch.kup.flomi.integration.AddressRepository;

@Path("/rest/address")
@Component(provide = Object.class)
public class AddressResource {
	private AddressRepository addressRepository;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Address createNewAddress(Address newAddress) {
		if (newAddress.getId() != null && newAddress.getId() != 0)
			throw new IllegalArgumentException("newAddress hat eine ID "
					+ newAddress.getId());
		return addressRepository.save(newAddress);
	}

	@DELETE
	@Path("{id}")
	public void deleteAddressById(@PathParam("id") Long id) {
		addressRepository.remove(addressRepository.findById(id));
	}

	@GET
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Address> findAddresses(
			@QueryParam("searchtext") String searchtext) {
		return getAddressRepository().findByText(searchtext);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Address> findAllAddresses() {
		return getAddressRepository().findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Address findById(@PathParam("id") Long id) {
		return addressRepository.findById(id);
	}

	public AddressRepository getAddressRepository() {
		return addressRepository;
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveAddress(@PathParam("id") Long id, Address address) {
		if (!id.equals(address.getId())) {
			throw new IllegalArgumentException();
		}
		addressRepository.save(address);
	}

	@Reference
	public void setAddressRepository(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

}
