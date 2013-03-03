package fr.ineatconseil.antwerp.entity;

import java.util.Arrays;
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
    private String self;
    private Player player1;
    private Player player2;
    private LinkedHashSet<Move> moves = new LinkedHashSet<>(9);
    private GameStatus status = GameStatus.WAITING_FOR_PLAYERS;
    private String winnerId;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
    public GameStatus getStatus() {
        return status;
    }
    
    public void setStatus(GameStatus status) {
        this.status = status;
    }
    
    public String getWinnerId() {
        return winnerId;
    }
    
    public void setWinnerId(String winnerId) {
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

    public Move[] getMoves() {
        return moves.toArray(new Move[moves.size()]);
    }
    public void setMoves(Move[] moves){
        this.moves.addAll(Arrays.asList(moves));
    }

    public Move addMove(Move move) {
        // is the game over ?
        if(GameStatus.OVER.equals(status) || moves.size() == 9) {
            throw new java.lang.UnsupportedOperationException("The game is over.");
        }
        
        //Does the move is played by one of the players ?
        if(!move.getPlayerURI().equals(player1.getSelf()) && !move.getPlayerURI().equals(player2.getSelf())) {
            throw new java.lang.UnsupportedOperationException("Sorry but ... who are you " + move.getPlayerURI() +" ?");
        }

        //Does the move is played by the right player ?
        Move lastMove =
                moves.size()==0
                        ? null
                        : moves.toArray(new Move[moves.size()])[moves.size()-1];

        if(lastMove != null && lastMove.getPlayerURI().equals(move.getPlayerURI())) {
            throw new java.lang.UnsupportedOperationException("It's not your turn, buddy!");
        }

        //Does the move have already been played ?
        for(Move m : moves) {
            if(m.getX()==move.getX() && m.getY()==move.getY()) {
                throw new java.lang.UnsupportedOperationException("Not again!");
            }
        }

        //ok, play!
        move.setId(moves.size()+1);
        moves.add(move);

        updateGameStatus(move.getPlayerURI());
        return move;
    }
    
    private void updateGameStatus(String playerURI) {
        if(moves.size() == 9) {
            status = GameStatus.OVER;
        } else {
            int[] xCount = new int[3];
            int[] yCount = new int[3];
            int diagSlashCount = 0;
            int diagBackSlashCount = 0;
            
            for(Move m : moves) {
                if(m!=null && m.getPlayerURI().equals(playerURI)) {
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
                winnerId = playerURI;
            }
        }
    }
}
