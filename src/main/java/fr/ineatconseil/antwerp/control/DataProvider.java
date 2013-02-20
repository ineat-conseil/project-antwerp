package fr.ineatconseil.antwerp.control;

import fr.ineatconseil.antwerp.entity.Game;
import fr.ineatconseil.antwerp.entity.Move;
import fr.ineatconseil.antwerp.entity.Player;
import java.util.Collection;
import java.util.HashMap;

public class DataProvider {
    
    private static HashMap<Long, Game> inMemoryGames = new HashMap<>();

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

    public static Game move(Long gameId, Move move) {
        Game game = getGame(gameId);
        move.setId(System.nanoTime());
        game.addMove(move);
        System.err.print(game.getStatus());
        return game;
    }
    
    public static Game addPlayer(Game game, Player player) {
        Game _game = inMemoryGames.get(game.getId());
        if (player.getId()==null) {
            player.setId(System.nanoTime());
        }
        if(_game.getPlayer1()==null) {
            _game.setPlayer1(player);
        } else if(_game.getPlayer2()==null) {
            _game.setPlayer2(player);
        }
        return _game;
    }
}