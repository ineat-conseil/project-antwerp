package fr.ineatconseil.antwerp.control;

import fr.ineatconseil.antwerp.entity.Game;
import fr.ineatconseil.antwerp.entity.Move;
import fr.ineatconseil.antwerp.entity.Player;
import org.glassfish.jersey.media.sse.EventChannel;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;

import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;

public class DataProvider {
    
    private static HashMap<Long, Game> inMemoryGames = new HashMap<>();
    private static SseBroadcaster sseBroadcaster = new SseBroadcaster();

    public static Collection<Game> getAllGames() {
        return inMemoryGames.values();
    }
    
    public static Game createGame(Game game) {
        game.setId(System.nanoTime());
        inMemoryGames.put(game.getId(), game);

        return game;
    }

    public static void removeGame(Long id) {
        inMemoryGames.remove(id);
    }
    
    public static Game getGame(Long id) {
        return inMemoryGames.get(id);
    }

    public static Move move(Long gameId, Move move) {
        Game game = getGame(gameId);
        move.setId(System.nanoTime());
        sseBroadcaster.broadcast((new OutboundEvent.Builder())
                .name("change")
                .data(Game.class, game)
                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                .build());
        return game.addMove(move);
    }
    
    public static Player addPlayer(Game game, Player player) {
        Game _game = inMemoryGames.get(game.getId());
        Player p=null;
        if (player.getId()==null) {
            player.setId(System.nanoTime());
        }
        if(_game.getPlayer1()==null) {
            _game.setPlayer1(player);
            p =_game.getPlayer1();
            sseBroadcaster.broadcast((new OutboundEvent.Builder())
                    .name("new")
                    .data(Game.class, _game)
                    .mediaType(MediaType.APPLICATION_JSON_TYPE)
                    .build());
        }
        else if(_game.getPlayer2()==null) {
            _game.setPlayer2(player);
            p=_game.getPlayer2();
            sseBroadcaster.broadcast((new OutboundEvent.Builder())
                    .name("change")
                    .data(Game.class, _game)
                    .mediaType(MediaType.APPLICATION_JSON_TYPE)
                    .build());
        }

        return p;
    }

    public static void addEventChannel(EventChannel ec) {
        sseBroadcaster.add(ec);
    }
}