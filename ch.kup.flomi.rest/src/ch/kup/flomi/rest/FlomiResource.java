package ch.kup.flomi.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.kup.flomi.domain.Flomi;
import ch.kup.flomi.integration.FlomiRepository;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Path("flomi")
@Component(provide=Object.class)
public class FlomiResource {
	private FlomiRepository flomiRepository;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flomi> findAllFlomis() {
		return flomiRepository.findAll();
	}
 

	public FlomiRepository getFlomiRepository() {
		return flomiRepository;
	}


	@Reference
	public void setFlomiRepository(FlomiRepository flomiRepository) {
		this.flomiRepository = flomiRepository;
	}
	
}
