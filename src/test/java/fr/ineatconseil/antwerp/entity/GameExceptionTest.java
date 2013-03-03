/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ineatconseil.antwerp.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author nicolasger
 */
public class GameExceptionTest {
    
    Game game;
    Player p1;
    Player p2;
    Player p3;
    
    @Before
    public void setUp() {
        game=new Game();
        p1 = new Player("Foo");
        p1.setId(1);
        p1.setSelf("1");
        p2 = new Player("Bar");
        p2.setId(2);
        p2.setSelf("2");
        p3 = new Player("Ineat");
        p3.setId(3);
        p3.setSelf("3");
        game.setPlayer1(p1);
        game.setPlayer2(p2);
    }
    
    @After
    public void tearDown() {
        game=null;
    }

    @Test(expected=UnsupportedOperationException.class)
    public void notYourTurn() {
        game.addMove(new Move(0, 0, p1.getSelf()));
        game.addMove(new Move(0, 1, p1.getSelf()));
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void sameMove() {
        game.addMove(new Move(0, 0, p1.getSelf()));
        game.addMove(new Move(0, 0, p2.getSelf()));
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void unknownPlayer() {
        game.addMove(new Move(0, 1, p3.getSelf()));
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void gameOver() {
        game.addMove(new Move(0, 0, p1.getSelf()));
        game.addMove(new Move(1, 0, p2.getSelf()));
        game.addMove(new Move(0, 1, p1.getSelf()));
        game.addMove(new Move(1, 1, p2.getSelf()));
        game.addMove(new Move(0, 2, p1.getSelf()));
        game.addMove(new Move(1, 2, p2.getSelf()));
    }
    
}
