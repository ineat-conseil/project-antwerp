package fr.ineatconseil.antwerp.entity;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class GameTest {
    
    Game game;
    Player p1;
    Player p2;
    
    @Before
    public void setUp() {
        game=new Game();
        p1 = new Player("Foo");
        p1.setId(1);
        p1.setSelf("1");
        p2 = new Player("Bar");
        p2.setId(2);
        p2.setSelf("2");
        game.setPlayer1(p1);
        game.setPlayer2(p2);
    }
    
    @After
    public void tearDown() {
        game=null;
    }
     
    /**
     *          0    1    2
     *     0    A    A    A
     *     1    B    B
     *     2
     */
    @Test
    public void line1() {
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        
        game.addMove(new Move(0, 0, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 0, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 1, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 1, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 2, p1.getSelf()));
        
        assertEquals(game.getStatus(), GameStatus.OVER);
        assertEquals(game.getWinnerId(), p1.getSelf());
    }
    
    /**
     *          0    1    2
     *     0    B    B
     *     1    A    A    A
     *     2
     */
    @Test
    public void line2() {
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        
        game.addMove(new Move(1, 0, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 0, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 1, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 1, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 2, p1.getSelf()));
        
        assertEquals(game.getStatus(), GameStatus.OVER);
        assertEquals(game.getWinnerId(), p1.getSelf());
    }
    
    /**
     *          0    1    2
     *     0    B    B
     *     1   
     *     2    A    A    A
     */
    @Test
    public void line3() {
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        
        game.addMove(new Move(2, 0, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 0, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(2, 1, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 1, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(2, 2, p1.getSelf()));
        
        assertEquals(game.getStatus(), GameStatus.OVER);
        assertEquals(game.getWinnerId(), p1.getSelf());
    }
    
    /**
     *          0    1    2
     *     0    A    B
     *     1    A    B
     *     2    A
     */
    @Test
    public void column1() {
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        
        game.addMove(new Move(0, 0, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 1, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 0, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 1, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(2, 0, p1.getSelf()));
        
        assertEquals(game.getStatus(), GameStatus.OVER);
        assertEquals(game.getWinnerId(), p1.getSelf());
    }
    
    /**
     *          0    1    2
     *     0    B    A
     *     1    B    A
     *     2         A
     */
    @Test
    public void column2() {
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        
        game.addMove(new Move(0, 1, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 0, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 1, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 0, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(2, 1, p1.getSelf()));
        
        assertEquals(game.getStatus(), GameStatus.OVER);
        assertEquals(game.getWinnerId(), p1.getSelf());
    }
    
    /**
     *          0    1    2
     *     0    B         A
     *     1    B         A
     *     2              A
     */
    @Test
    public void column3() {
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        
        game.addMove(new Move(0, 2, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 0, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 2, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 0, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(2, 2, p1.getSelf()));
        
        assertEquals(game.getStatus(), GameStatus.OVER);
        assertEquals(game.getWinnerId(), p1.getSelf());
    }
    
    /**
     *          0    1    2
     *     0    A    B
     *     1    B    A
     *     2              A
     */
    @Test
    public void diagonal1() {
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        
        game.addMove(new Move(0, 0, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 0, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 1, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 1, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(2, 2, p1.getSelf()));
        
        assertEquals(game.getStatus(), GameStatus.OVER);
        assertEquals(game.getWinnerId(), p1.getSelf());
    }
    
    /**
     *          0    1    2
     *     0    B         A
     *     1    B    A
     *     2    A
     */
    @Test
    public void diagonal2() {
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        
        game.addMove(new Move(0, 2, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 0, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 1, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 0, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(2, 0, p1.getSelf()));
        
        assertEquals(game.getStatus(), GameStatus.OVER);
        assertEquals(game.getWinnerId(), p1.getSelf());
    }
    
    
    
    /**
     *          0    1    2
     *     0    A    B    A
     *     1    A    B    B
     *     2    B    A    A
     */
    @Test
    public void noWinner() {
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        
        game.addMove(new Move(0, 0, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 1, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(0, 2, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        
        game.addMove(new Move(1, 1, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 0, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(1, 2, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        
        game.addMove(new Move(2, 1, p1.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(2, 0, p2.getSelf()));
        assertEquals(game.getStatus(), GameStatus.PLAYING);
        game.addMove(new Move(2, 2, p1.getSelf()));
        
        assertEquals(game.getStatus(), GameStatus.OVER);
        assertNull(game.getWinnerId());
    }
}
