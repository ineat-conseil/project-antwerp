/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ineatconseil.antwerp.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nicolasger
 */
public class GameStatusTest {
    
    Game game;
    @Before
    public void setUp() {
        game=new Game();
    }
    
    @After
    public void tearDown() {
        game=null;
    }
    
    @Test
    public void statusNoPlayers() {
        assertEquals(game.getStatus(), GameStatus.WAITING_FOR_PLAYERS);
    }
    
    @Test
    public void statusOnePlayers() {
        Player p = new Player("Foo");
        p.setId(1);
        game.setPlayer1(p);
        assertEquals(game.getStatus(), GameStatus.WAITING_FOR_PLAYERS);
    }
    
    @Test
    public void statusTwoPlayers() {
        Player p1 = new Player("Foo");
        p1.setId(1);
        Player p2 = new Player("John");
        p2.setId(2);
        game.setPlayer1(p1);
        game.setPlayer2(p2);
        assertEquals(game.getStatus(), GameStatus.PLAYING);
    }
}
