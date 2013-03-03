package fr.ineatconseil.antwerp.entity;

public class Player {

    private Integer id;
    private String self;
    private String nickname;
    
    public Player(){}

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Player(String nickname) {
        this.nickname = nickname;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}