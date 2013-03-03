package fr.ineatconseil.antwerp.entity;


public class Move {

    private Integer id;
    private String self;
    private int x;
    private int y;
    private String playerURI;

    public Move() {}
    public Move(int x, int y, String playerURI) {
        this.x = x;
        this.y = y;
        this.playerURI = playerURI;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
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

    public String getPlayerURI() {
        return playerURI;
    }

    public void setPlayerURI(String playerURI) {
        this.playerURI = playerURI;
    }
 }
