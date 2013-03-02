package fr.ineatconseil.antwerp.entity;


public class Move {

    private Integer id;
    private int x;
    private int y;
    private Integer playerId;

    public Move() {}
    public Move(int x, int y, Integer playerId) {
        this.x = x;
        this.y = y;
        this.playerId = playerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
 }
