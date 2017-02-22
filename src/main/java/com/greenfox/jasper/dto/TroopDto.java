package com.greenfox.jasper.dto;

public class TroopDto {

    private long id;
    private int level;
    private int hp;
    private int attack;
    private int defense;
    private long upgradeTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "TroopDto{" +
                "id=" + id +
                ", level=" + level +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", upgradeTime=" + upgradeTime +
                '}';
    }
}
