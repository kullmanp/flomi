package ch.kup.flomi.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import aQute.bnd.annotation.component.Component;

@Path("flomi")
@Component(provide=Object.class)
public class FlomiResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "HELLLO REST WORLD";
	}
	
}
