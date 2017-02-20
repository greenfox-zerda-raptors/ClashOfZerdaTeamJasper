package com.greenfox.jasper.dto;

import com.greenfox.jasper.domain.Troop;

import java.util.List;

public class BattleResponseDto {

    private int attackerId;
    private List<Troop> attackerTroops;
    private int attackerDamage;
    private List<Troop> attackerCasualties;
    private int defenderId;
    private List<Troop> defenderTroops;
    private int defenderDamage;
    private List<Troop> defenderCasualties;

    public int getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(int attackerId) {
        this.attackerId = attackerId;
    }

    public List<Troop> getAttackerTroops() {
        return attackerTroops;
    }

    public void setAttackerTroops(List<Troop> attackerTroops) {
        this.attackerTroops = attackerTroops;
    }

    public int getAttackerDamage() {
        return attackerDamage;
    }

    public void setAttackerDamage(int attackerDamage) {
        this.attackerDamage = attackerDamage;
    }

    public List<Troop> getAttackerCasualties() {
        return attackerCasualties;
    }

    public void setAttackerCasualties(List<Troop> attackerCasualties) {
        this.attackerCasualties = attackerCasualties;
    }

    public int getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(int defenderId) {
        this.defenderId = defenderId;
    }

    public List<Troop> getDefenderTroops() {
        return defenderTroops;
    }

    public void setDefenderTroops(List<Troop> defenderTroops) {
        this.defenderTroops = defenderTroops;
    }

    public int getDefenderDamage() {
        return defenderDamage;
    }

    public void setDefenderDamage(int defenderDamage) {
        this.defenderDamage = defenderDamage;
    }

    public List<Troop> getDefenderCasualties() {
        return defenderCasualties;
    }

    public void setDefenderCasualties(List<Troop> defenderCasualties) {
        this.defenderCasualties = defenderCasualties;
    }

    @Override
    public String toString() {
        return "BattleResponseDto{" +
                "attackerId=" + attackerId +
                ", attackerTroops=" + attackerTroops +
                ", attackerDamage=" + attackerDamage +
                ", attackerCasualties=" + attackerCasualties +
                ", defenderId=" + defenderId +
                ", defenderTroops=" + defenderTroops +
                ", defenderDamage=" + defenderDamage +
                ", defenderCasualties=" + defenderCasualties +
                '}';
    }
}
