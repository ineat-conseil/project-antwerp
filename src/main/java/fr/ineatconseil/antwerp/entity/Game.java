package fr.ineatconseil.antwerp.entity;

import java.util.LinkedHashSet;
import java.util.logging.Logger;

/**
 * Represent a game between 2 players. 
 * All moves are recorded chronologically.
 * the game is about tic tac toe, so moves represent actions on a grid  of 3x3
 * @author nicolasger
 */
public class Game {
    
    private Long id;
    private Player player1;
    private Player player2;
    private LinkedHashSet<Move> moves = new LinkedHashSet<>();
    private GameStatus status = GameStatus.WAITING_FOR_PLAYERS;
    private Long winnerId;

    public GameStatus getStatus() {
        return status;
    }
    
    public void setStatus(GameStatus status) {
        this.status = status;
    }
    
    public Long getWinnerId() {
        return winnerId;
    }
    
    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
        status = GameStatus.PLAYING;
    }

    public LinkedHashSet<Move> getMoves() {
        return moves;
    }
    public void setMoves(LinkedHashSet<Move> moves){
        this.moves = moves;
    }

    public Move addMove(Move move) {
        // is the game over ?
        if(GameStatus.OVER.equals(status)) {
            throw new java.lang.UnsupportedOperationException("The game is over.");
        }
        
        //Does the move is played by one of the players ?
        if(!move.getPlayerId().equals(player1.getId()) && !move.getPlayerId().equals(player2.getId())) {
            throw new java.lang.UnsupportedOperationException("Sorry but ... who are you ?");
        }
        
        //Does the move is played by the right player ?
        Move lastMove = 
                moves.size()==0
                ? null
                : moves.toArray(new Move[moves.size()])[moves.size()-1];
        
        if(lastMove != null && lastMove.getPlayerId().equals(move.getPlayerId())) {
            throw new java.lang.UnsupportedOperationException("It's not your turn, buddy!");
        }
        
        //Does the move have already been played ?
        for(Move m : moves) {
            if(m.getX()==move.getX() && m.getY()==move.getY()) {
                throw new java.lang.UnsupportedOperationException("Not again!");
            }
        }
        
        //ok, play!
        moves.add(move);

        updateGameStatus(move.getPlayerId());
        return move;
    }
    
    private void updateGameStatus(long playerId) {
        Logger.getLogger(this.getClass().getName()).info("##"+moves.size());
        if(moves.size()==9) {
            status = GameStatus.OVER;
        } else {
            int[] xCount = new int[3];
            int[] yCount = new int[3];
            int diagSlashCount = 0;
            int diagBackSlashCount = 0;
            
            for(Move m : moves) {
                if(m.getPlayerId() == playerId) {
                    xCount[m.getX()] = xCount[m.getX()]+1;
                    yCount[m.getY()] = yCount[m.getY()]+1;
                    if(m.getX()==m.getY()) {
                        diagBackSlashCount++;
                        diagSlashCount++;
                    } else if((m.getX()+m.getY())==2) {
                        diagSlashCount++;
                    }
                }
            }
            if(   xCount[0]==3
               || xCount[1]==3
               || xCount[2]==3
               || yCount[0]==3
               || yCount[1]==3
               || yCount[2]==3
               || diagBackSlashCount==3
               || diagSlashCount==3) {
                status = GameStatus.OVER;
                winnerId = playerId;
            }
        }
    }
}
