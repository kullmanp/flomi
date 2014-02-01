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
import javax.ws.rs.core.MediaType;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.Flomi;
import ch.kup.flomi.integration.FlomiRepository;

@Path("flomi")
@Component(provide = Object.class)
public class FlomiResource {
	private FlomiRepository flomiRepository;

	@POST
	public Flomi createNewFlomi(Flomi newFlomi) {
		if (newFlomi.getId() != null && newFlomi.getId() != 0)
			throw new IllegalArgumentException();
		return flomiRepository.save(newFlomi);
	}

	@DELETE
	@Path("{id}")
	public void deleteFlomiById(@PathParam("id") Long id) {
		flomiRepository.remove(flomiRepository.findById(id));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flomi> findAllFlomis() {
		return flomiRepository.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Flomi getFlomiById(@PathParam("id") Long id) {
		return flomiRepository.findById(id);
	}

	public FlomiRepository getFlomiRepository() {
		return flomiRepository;
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveFlomi(@PathParam("id") Long id, Flomi flomi) {
		if (!id.equals(flomi.getId())) {
			throw new IllegalArgumentException();
		}
		flomiRepository.save(flomi);
	}

	@Reference
	public void setFlomiRepository(FlomiRepository flomiRepository) {
		this.flomiRepository = flomiRepository;
	}

}
