package fr.ineatconseil.antwerp.entity;

public class Move {

    private Long id;
    private int x;
    private int y;
    private Long playerId;

    public Move() {}
    public Move(int x, int y, Long playerId) {
        this.x = x;
        this.y = y;
        this.playerId = playerId;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
 }
