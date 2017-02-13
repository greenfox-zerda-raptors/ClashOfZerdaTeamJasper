package com.greenfox.jasper.domain;

/**
 * Created by almasics on 2017.02.08..
 */
public class ArmyStats {

    private int totalHp;
    private int totalAttack;
    private int totalDefense;
    private Kingdom kingdom;

    public ArmyStats(int totalHp, int totalAttack, int totalDefense, Kingdom kingdom) {
        this.totalHp = totalHp;
        this.totalAttack = totalAttack;
        this.totalDefense = totalDefense;
        this.kingdom = kingdom;
    }

    public int getTotalHp() {
        return totalHp;
    }

    public void setTotalHp(int totalHp) {
        this.totalHp = totalHp;
    }

    public int getTotalAttack() {
        return totalAttack;
    }

    public void setTotalAttack(int totalAttack) {
        this.totalAttack = totalAttack;
    }

    public int getTotalDefense() {
        return totalDefense;
    }

    public void setTotalDefense(int totalDefense) {
        this.totalDefense = totalDefense;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

}
