package com.greenfox.jasper.dto;

/**
 * Created by almasics on 2017.02.08..
 */
public class BattleResultDto {
    private String attackerName;
    private String defenderName;
    private int damageDoneByAttacker;
    private int damageDoneByDefender;


    public BattleResultDto(String attackerName, String defenderName, int damageDoneByAttacker, int damageDoneByDefender) {
        this.attackerName = attackerName;
        this.defenderName = defenderName;
        this.damageDoneByAttacker = damageDoneByAttacker;
        this.damageDoneByDefender = damageDoneByDefender;
    }

    public int getDamageDoneByAttacker() {
        return damageDoneByAttacker;
    }

    public void setDamageDoneByAttacker(int damageDoneByAttacker) {
        this.damageDoneByAttacker = damageDoneByAttacker;
    }

    public int getDamageDoneByDefender() {
        return damageDoneByDefender;
    }

    public void setDamageDoneByDefender(int damageDoneByDefender) {
        this.damageDoneByDefender = damageDoneByDefender;
    }

    public String getAttackerName() {
        return attackerName;
    }

    public void setAttackerName(String attackerName) {
        this.attackerName = attackerName;
    }

    public String getDefenderName() {
        return defenderName;
    }

    public void setDefenderName(String defenderName) {
        this.defenderName = defenderName;
    }
}
