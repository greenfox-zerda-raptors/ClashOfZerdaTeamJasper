package com.greenfox.jasper.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "troop_table")
@Component
public class Troop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "troop_id")
    private long troopId;

    @Column(name = "troop_level")
    private int level;

    private int hp;

    private int attack;

    private int defense;

    @Column(name="upgrade_time")
    private long upgradeTime = 0;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "kingdom_id")
    private Kingdom kingdom;

    public Troop() {
        this.level = 0;
        this.hp = 20;
        this.attack = 10;
        this.defense = 5;
    }

    
    public Troop(int id) {
        this.troopId = id;
        this.level = 1;
        this.hp = 20;
        this.attack = 10;
        this.defense = 5;
    }

    public Troop(Troop troop) {
        this.level = troop.getLevel();
        this.hp = troop.getHp();
        this.attack = troop.getAttack();
        this.defense = troop.getDefense();
    }

    public Troop(Kingdom kingdom) {
        this.level = 0;
        this.hp = 20;
        this.attack = 10;
        this.defense = 5;
        this.kingdom = kingdom;
    }

    public void upgrade(){
        this.level++;
        this.upgradeTime = 0;
        this.attack++;
        this.defense++;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
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

    public long getUpgradeTime() {
        return upgradeTime;
    }

    public void setUpgradeTime(long upgradeTime) {
        this.upgradeTime = upgradeTime;
    }
}
