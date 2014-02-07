package ch.kup.flomi.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.FlomiBuchung;
import ch.kup.flomi.integration.FlomiBuchungRepository;

@Path("/rest/reservations")
@Component(provide = Object.class)
public class ReservationResource {
	private FlomiBuchungRepository flomiBuchungRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FlomiBuchung> findAllReservations() {
		return flomiBuchungRepository.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public FlomiBuchung findById(@PathParam("id") Long id) {
		return flomiBuchungRepository.findById(id);
	}

	public FlomiBuchungRepository getAddressRepository() {
		return flomiBuchungRepository;
	}

	@Reference
	public void setAddressRepository(
			FlomiBuchungRepository flomiBuchungRepository) {
		this.flomiBuchungRepository = flomiBuchungRepository;
	}

}
