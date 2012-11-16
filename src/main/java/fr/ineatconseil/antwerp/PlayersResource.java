/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ineatconseil.antwerp;

import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author nicolasger
 */
@Path("players")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayersResource {
    
    
    @GET
    public Collection<Player> getAll() {
        return DataProvider.getAllPlayers();
    }
    
    @GET
    @Path("{id:[0-9]+}")
    public Player getPlayer(@PathParam("id") Long id) {
        return DataProvider.getPlayer(id);
    }
    
    @POST
    public Response createPlayer(@Context UriInfo uriInfo, Player player) {
        return Response.created(
                uriInfo.getBaseUriBuilder()
                .path(PlayersResource.class)
                .path("{id}")
                .build(DataProvider.createPlayer(player)))
              .build();
    }
}
