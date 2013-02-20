package fr.ineatconseil.antwerp.control;

import fr.ineatconseil.antwerp.entity.Game;
import fr.ineatconseil.antwerp.entity.Move;
import fr.ineatconseil.antwerp.entity.Player;
import java.util.Collection;
import java.util.HashMap;

public class DataProvider {
    
    private static HashMap<Long, Game> inMemoryGames = new HashMap<>();
    private static HashMap<Long, Player> inMemoryPlayers = new HashMap<>();
    
    /*
     * GAME
     */
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
    
    /*
     * MOVE
     */
    public static Game move(Long gameId, Move move) {
        Game game = getGame(gameId);
        move.setId(System.nanoTime());
        game.addMove(move);
        return game;
    }
    
    /*
     * PLAYER
     */
    public static Player createPlayer(Player p) {
        p.setId(System.nanoTime());
        inMemoryPlayers.put(p.getId(), p);
        return p;
    }
    
    public static Collection<Player> getAllPlayers() {
        return inMemoryPlayers.values();
    }
    
    public static Player getPlayer(Long id) {
        return inMemoryPlayers.get(id);
    }
    
    public static Game addPlayer(Game game, Player player) {
        Game _game = inMemoryGames.get(game.getId());
        _game.setPlayer2(player);
        return _game;
    }
}
