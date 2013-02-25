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
        p1.setId(System.nanoTime());
        p2 = new Player("John");
        p2.setId(System.nanoTime());
        p3 = new Player("Ineat");
        p3.setId(System.nanoTime());
        game.setPlayer1(p1);
        game.setPlayer2(p2);
    }
    
    @After
    public void tearDown() {
        game=null;
    }

    @Test(expected=UnsupportedOperationException.class)
    public void notYourTurn() {
        game.addMove(new Move(0, 0, p1.getId()));
        game.addMove(new Move(0, 1, p1.getId()));
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void sameMove() {
        game.addMove(new Move(0, 0, p1.getId()));
        game.addMove(new Move(0, 0, p2.getId()));
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void unknownPlayer() {
        game.addMove(new Move(0, 1, p3.getId()));
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void gameOver() {
        game.addMove(new Move(0, 0, p1.getId()));
        game.addMove(new Move(1, 0, p2.getId()));
        game.addMove(new Move(0, 1, p1.getId()));
        game.addMove(new Move(1, 1, p2.getId()));
        game.addMove(new Move(0, 2, p1.getId()));
        game.addMove(new Move(1, 2, p2.getId()));
    }
    
}
