package fr.ineatconseil.antwerp.client;

import fr.ineatconseil.antwerp.entity.Game;
import fr.ineatconseil.antwerp.entity.GameStatus;
import fr.ineatconseil.antwerp.entity.Move;
import fr.ineatconseil.antwerp.entity.Player;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientFactory;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonBinder;

/**
 * mvn exec:java -Dexec.mainClass="fr.ineatconseil.antwerp.client.RestClient" -Dexec.classpathScope=compile
 */
public class RestClient {

    private static final String API_URL = "http://localhost:8080/project-antwerp/api";
    private static final String PLAYERS_URL = API_URL + "/players";
    private static final String GAMES_URL = API_URL + "/games";
    private static final MediaType JSON = MediaType.APPLICATION_JSON_TYPE;
    
    private final Client rsClient;
    private final WebTarget  playerResource;
    private final WebTarget gameResource;
    private static final Logger log = Logger.getLogger(RestClient.class.getName());
    
    public RestClient() {
        rsClient = ClientFactory.newClient(
                new ClientConfig().binders(
                new MoxyJsonBinder()));
        
        playerResource = rsClient.target(PLAYERS_URL);
        gameResource = rsClient.target(GAMES_URL);
    }

    public List<Player> getPlayerList() {
       return playerResource.request(JSON).get(new GenericType<List<Player>>(){});
    }
    
    public URI addPlayer(Player player) {
        Entity<Player> jsonPlayer = Entity.json(player);
        Response response = playerResource.request(JSON).post(jsonPlayer);
        return response.getLocation();
    }
    
    public Player getPlayer(URI uri) {
        return rsClient.target(uri).request(JSON).get(Player.class);
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
    
    public void joinGame(URI game, Player player2) {
        Entity<Player> jsonPlayer = Entity.json(player2);
        rsClient.target(game).request(JSON).post(jsonPlayer);
    }
    
    public String displayPlayer(Player p) {
        return "=========> " + p.getFirstName() + " " + p.getLastName() + " (id:" + p.getId() + ") is connected.";
    }
    
    public String displayGame(Game g) {
        return "=========> game n°" + g.getId()
                + " - status: " + g.getStatus()
                + " - player1 id:" + (g.getPlayer1()==null?"?":g.getPlayer1().getId())
                + " - player2 id:" + (g.getPlayer2()==null?"?":g.getPlayer2().getId());
                    
    }
    
    public void move(URI game, Player p, int x, int y) {
        Move m = new Move(x, y, p.getId());
        Entity<Move> jsonMove = Entity.json(m);
        rsClient.target(game+"/moves").request(JSON).post(jsonMove);
    }
    
    
    public static void main(String... args) {
        RestClient rc = new RestClient();
    
        log.log(Level.INFO, "=========> {0} players", rc.getPlayerList().size());
        log.log(Level.INFO, "=========> {0} games", rc.getGameList().size());
        
        URI johnDoeURI = rc.addPlayer(new Player("John", "Doe"));
        URI fooBarURI = rc.addPlayer(new Player("Foo", "Bar"));
        
        List<Player> players = rc.getPlayerList();
        log.log(Level.INFO, "=========> {0} players", players.size());
        for(Player p : players) {
            log.log(Level.INFO,rc.displayPlayer(p));
        }
        
        Player johnDoe = rc.getPlayer(johnDoeURI);
        Player fooBar = rc.getPlayer(fooBarURI);
        
        Game newGame = new Game();
        newGame.setPlayer1(fooBar);
        URI fooBarGameURI = rc.createGame(newGame);
        List<Game> games = rc.getGameList();
        log.log(Level.INFO, "=========> {0} games", games.size());
        for(Game game : games) {
            log.log(Level.INFO,rc.displayGame(game));
        }
        
        rc.joinGame(fooBarGameURI, johnDoe);
        
        Game game = rc.getGame(fooBarGameURI);
        log.log(Level.INFO, rc.displayGame(game));
        
        for(int i = 0; GameStatus.PLAYING.equals(game.getStatus()); i++) {
            rc.move(fooBarGameURI, fooBar, 0, i);
            log.log(Level.INFO, "=========> Foo Bar moves to {0}/{1}", new Integer[]{0, i});
            rc.move(fooBarGameURI, johnDoe, 1, i);
            log.log(Level.INFO, "=========> John Doe moves to {0}/{1}", new Integer[]{1, i});
            game = rc.getGame(fooBarGameURI);
        }
        
        log.log(Level.INFO, rc.displayGame(game));
        log.log(Level.INFO, "=========> The winner is {0}", game.getWinnerId());
    }
}
