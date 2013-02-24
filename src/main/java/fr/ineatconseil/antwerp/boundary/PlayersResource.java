package fr.ineatconseil.antwerp.boundary;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("players")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayersResource {
    /**
     * get a player by id
     * @param id
     * @return 
     */
    @GET
    @Path("{id:[0-9]+}")
    public Response getPlayer(@PathParam("id") Long id) {
       return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
}
