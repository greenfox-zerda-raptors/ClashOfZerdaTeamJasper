package com.greenfox.jasper.domain;


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

    public Troop() {
        this.level = 0;
        this.hp = 20;
        this.attack = 10;
        this.defense = 5;
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
