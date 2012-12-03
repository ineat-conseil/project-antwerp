/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ineatconseil.antwerp;

import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author nicolasger
 */
@Path("games")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GamesResource {
   
    
    public Collection<Game> getAll() {
        return DataProvider.getAllGames();
    }
    
    public void createGame(Player player) {
        
    }
}
