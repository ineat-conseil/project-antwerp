package fr.ineatconseil.antwerp.boundary;

import fr.ineatconseil.antwerp.control.DataProvider;
import fr.ineatconseil.antwerp.entity.Game;
import fr.ineatconseil.antwerp.entity.Move;
import fr.ineatconseil.antwerp.entity.Player;
import java.util.Collection;
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

@Path("games")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GamesResource {
    
    /**
     * create a new game (without any players)
     * @param uriInfo
     * @param game
     * @return 
     */
    @POST
    public Response createGame(@Context UriInfo uriInfo, Game game) {
        return Response.created(
                uriInfo.getBaseUriBuilder()
                .path(GamesResource.class)
                .path("{id}")
                .build(DataProvider.createGame(game).getId()))
              .build();
    }
    
    /**
     * add a player into a game
     * @param uriInfo
     * @param gameId 
     * @return
     */
    @POST
    @Path("{id:[0-9]+}/players")
    public Response addPlayer(@Context UriInfo uriInfo, @PathParam("id") Long gameId, Player player) {
        Game game = DataProvider.getGame(gameId);
        if(game.getPlayer1()!=null && game.getPlayer2()!=null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Player p = DataProvider.addPlayer(game, player);
        return Response.created(
                uriInfo.getBaseUriBuilder()
                .path(PlayersResource.class)
                .path("{id}")
                .build(player.getId()))
              .build();
    }
    
    /**
     * get all games
     * @return 
     */
    @GET
    public Collection<Game> getAll() {
        return DataProvider.getAllGames();
    } 
    
    /**
     * get a game by id
     * @param id
     * @return 
     */
    @GET
    @Path("{id:[0-9]+}")
    public Game getGame(@PathParam("id") Long id) {
        return DataProvider.getGame(id);
    }
 
    /**
     * play a move on a game
     * @param uriInfo
     * @param gameId
     * @param move
     * @return 
     */
    @POST
    @Path("{id:[0-9]+}/moves")
    public Response move(@Context UriInfo uriInfo,@PathParam("id") Long gameId, Move move) {
        Move _move = DataProvider.move(gameId, move);
        return Response.created(
                uriInfo.getBaseUriBuilder()
                .path(MovesResource.class)
                .path("{id}")
                .build(_move.getId()))
              .build();
    } 
}