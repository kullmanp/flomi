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
import ch.kup.flomi.domain.Tisch;
import ch.kup.flomi.integration.TischRepository;

@Path("/rest/tables")
@Component(provide = Object.class)
public class TischResource {
	private TischRepository tischRepository;

	@POST
	public Tisch createNewTisch(Tisch newTisch) {
		if (newTisch.getId() != null && newTisch.getId() != 0)
			throw new IllegalArgumentException();
		return tischRepository.save(newTisch);
	}

	@DELETE
	@Path("{id}")
	public void deleteTischById(@PathParam("id") Long id) {
		tischRepository.remove(tischRepository.findById(id));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tisch> findAll() {
		return tischRepository.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Tisch getTischById(@PathParam("id") Long id) {
		return tischRepository.findById(id);
	}

	public TischRepository getTischRepository() {
		return tischRepository;
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveTisch(@PathParam("id") Long id, Tisch Tisch) {
		if (!id.equals(Tisch.getId())) {
			throw new IllegalArgumentException();
		}
		tischRepository.save(Tisch);
	}

	@Reference
	public void setTischRepository(TischRepository tischRepository) {
		this.tischRepository = tischRepository;
	}

}
