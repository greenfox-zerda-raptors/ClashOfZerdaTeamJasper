package com.greenfox.jasper.domain;

/**
 * Created by almasics on 2017.02.08..
 */
public class BattleResult {
    private Kingdom attacker;
    private Kingdom defender;
    private int damageDoneByAttacker;
    private int damageDoneByDefender;


    public BattleResult(Kingdom attacker, Kingdom defender, int damageDoneByAttacker, int damageDoneByDefender) {
        this.attacker = attacker;
        this.defender = defender;
        this.damageDoneByAttacker = damageDoneByAttacker;
        this.damageDoneByDefender = damageDoneByDefender;
    }

    public String battleToString() {
        return String.format("damage done by attacker %d, damage done by defender %d", damageDoneByAttacker, damageDoneByDefender);
    }
}
