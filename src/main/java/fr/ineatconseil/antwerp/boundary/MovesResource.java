package fr.ineatconseil.antwerp.boundary;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("moves")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovesResource {

    /**
     * get a move by id
     * @param id
     * @return 
     */
    @GET
    @Path("{id:[0-9]+}")
    public Response getMove(@PathParam("id") Long id) {
        throw new NotSupportedException();
    }
}
