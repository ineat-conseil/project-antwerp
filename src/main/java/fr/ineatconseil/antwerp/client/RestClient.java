package fr.ineatconseil.antwerp.client;

import fr.ineatconseil.antwerp.entity.Game;
import fr.ineatconseil.antwerp.entity.GameStatus;
import fr.ineatconseil.antwerp.entity.Move;
import fr.ineatconseil.antwerp.entity.Player;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientFactory;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import junit.framework.Assert;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * mvn exec:java -Dexec.mainClass="fr.ineatconseil.antwerp.client.RestClient" -Dexec.classpathScope=compile
 */
public class RestClient {

    private static final String API_URL = "http://localhost:8080/project-antwerp/api";
    private static final String GAMES_URL = API_URL + "/games";
    private static final MediaType JSON = MediaType.APPLICATION_JSON_TYPE;
    
    private final Client rsClient;
    private final WebTarget gameResource;
    private static final Logger log = Logger.getLogger(RestClient.class.getName());
    
    public RestClient() {
        rsClient = ClientFactory.newClient(
                new ClientConfig()
                        .register(
                                JacksonFeature.class
                                ));
        gameResource = rsClient.target(GAMES_URL);
    }

    public URI addPlayer(URI gameURI, Player player) {
        Entity<Player> jsonPlayer = Entity.json(player);
        Response response = rsClient.target(gameURI.toString()+"/players").request(JSON).post(jsonPlayer);
        return response.getLocation();
    }
    
    public Game getGame(URI uri) {
        return rsClient.target(uri).request(JSON).get(Game.class);
    }

    public List<Game> getGameList() {
        return gameResource.request(JSON).get(new GenericType<List<Game>>(){});
    }
    
    public URI createGame(Game game) {
        Entity<Game> jsonGame = Entity.json(game);
        Response response = gameResource.request(JSON).post(jsonGame);
        return response.getLocation();
    }
    
    public String displayGame(Game g) {
        return "=========> game n°" + g.getId()
                + " - status: " + g.getStatus()
                + " - player1 id:" + (g.getPlayer1()==null?"?":g.getPlayer1().getId())
                + " - player2 id:" + (g.getPlayer2()==null?"?":g.getPlayer2().getId());
    }
    
    public URI move(URI game, Player p, int x, int y) {
        Move m = new Move(x, y, p.getId());
        Entity<Move> jsonMove = Entity.json(m);
        Response response = rsClient.target(game+"/moves").request(JSON).post(jsonMove);
        return response.getLocation();
    }
    
    
    public static void main(String... args) {
        RestClient rc = new RestClient();
    
        log.log(Level.INFO, "=========> {0} games", rc.getGameList().size());
        
        Player johnDoe = new Player("John");
        Player fooBar = new Player("Foo");

        Game newGame = new Game();
        URI fooBarGameURI = rc.createGame(newGame);
        List<Game> games = rc.getGameList();
        log.log(Level.INFO, "=========> {0} games", games.size());
        for(Game game : games) {
            log.log(Level.INFO,rc.displayGame(game));
        }
        
        URI playerUri = rc.addPlayer(fooBarGameURI, fooBar);
        rc.addPlayer(fooBarGameURI, johnDoe);
        
        //test not supported Exception
        try {
            rc.rsClient.target(playerUri).request(JSON).get(Player.class);
            throw new RuntimeException("something's wrong");
        } catch (ServerErrorException e) {
            Assert.assertEquals(501, e.getResponse().getStatus());
            log.log(Level.INFO, "get a player is not implemented");
        }
        
        Game game = rc.getGame(fooBarGameURI);
        log.log(Level.INFO, rc.displayGame(game));

        //get players with their ids
        johnDoe = game.getPlayer1();
        fooBar = game.getPlayer2();
        
        URI moveUri = null;
        for(int i = 0; GameStatus.PLAYING.equals(game.getStatus()); i++) {
            moveUri = rc.move(fooBarGameURI, fooBar, 0, i);
            log.log(Level.INFO, "=========> Foo Bar moves to {0}/{1}", new Integer[]{0, i});
            rc.move(fooBarGameURI, johnDoe, 1, i);
            log.log(Level.INFO, "=========> John Doe moves to {0}/{1}", new Integer[]{1, i});
            game = rc.getGame(fooBarGameURI);
        }
        //test not supported Exception
        try {
            rc.rsClient.target(moveUri).request(JSON).get(Move.class);
            throw new RuntimeException("something's wrong");
        } catch (ServerErrorException e) {
            Assert.assertEquals(501, e.getResponse().getStatus());
            log.log(Level.INFO, "get a move is not implemented");
        }
        
        log.log(Level.INFO, rc.displayGame(game));
        log.log(Level.INFO, "=========> The winner is {0}", game.getWinnerId());
    }
}
