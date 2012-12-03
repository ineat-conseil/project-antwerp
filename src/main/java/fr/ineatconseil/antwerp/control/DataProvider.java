/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ineatconseil.antwerp;

import java.util.Collection;
import java.util.HashMap;
import javax.ejb.Stateless;

/**
 *
 * @author nicolasger
 */
public class DataProvider {
    
    private static HashMap<Long, Game> inMemoryGames = new HashMap<>();
    private static HashMap<Long, Player> inMemoryPlayers = new HashMap<>();
    
    
    
    public static Collection<Game>getAllGames() {
        return inMemoryGames.values();
    }
    
    public static void addGame(Game game) {
        inMemoryGames.put(game.getId(), game);
    }
    
    public static void removeGame(Long id) {
        inMemoryGames.remove(id);
    }
    
    public static long createPlayer(Player p) {
        inMemoryPlayers.put(p.getId(), p);
        return p.getId();
    }
    
    public static Collection<Player> getAllPlayers() {
        return inMemoryPlayers.values();
    }
    
    public static Player getPlayer(Long id) {
        return inMemoryPlayers.get(id);
    }
}
