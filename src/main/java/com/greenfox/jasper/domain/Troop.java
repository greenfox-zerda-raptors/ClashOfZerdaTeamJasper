package com.greenfox.jasper.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "troops")
@Component
public class Troop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long troopId;
    private int level;
    private int hp;
    private int attack;
    private int defense;
    @ManyToOne
    @JsonIgnore
    private User user;

    public Troop() {
        this.level = 0;
        this.hp = 20;
        this.attack = 10;
        this.defense = 5;
    }

    public Troop(User user) {
        this.level = 0;
        this.hp = 20;
        this.attack = 10;
        this.defense = 5;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTroopId() {
        return troopId;
    }

    public void setTroopId(long troopId) {
        this.troopId = troopId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
