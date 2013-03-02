package fr.ineatconseil.antwerp.entity;

public class Player {

    private Integer id;
    private String nickname;
    
    public Player(){}

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