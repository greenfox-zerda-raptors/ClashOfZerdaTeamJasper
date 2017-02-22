package com.greenfox.jasper.dto;

import com.greenfox.jasper.domain.Troop;

import java.util.List;

public class BattleResponseDto {

    private List<Long> defenderLostBuildingIds;
    private  List<Long> attackerTroopIds;
    private List<Long> defenderTroopIds;
    private List<Long> lostAttackerTroopIds;
    private List<Long> lostDefenderTroopIds;
    private int attackerDamageDone;
    private int defenderDamageDone;
    private long attackerId;
    private long defenderId;

    public List<Long> getDefenderLostBuildingIds() {
        return defenderLostBuildingIds;
    }

    public void setDefenderLostBuildingIds(List<Long> defenderLostBuildingIds) {
        this.defenderLostBuildingIds = defenderLostBuildingIds;
    }

    public List<Long> getAttackerTroopIds() {
        return attackerTroopIds;
    }

    public void setAttackerTroopIds(List<Long> attackerTroopIds) {
        this.attackerTroopIds = attackerTroopIds;
    }

    public List<Long> getDefenderTroopIds() {
        return defenderTroopIds;
    }

    public void setDefenderTroopIds(List<Long> defenderTroopIds) {
        this.defenderTroopIds = defenderTroopIds;
    }

    public List<Long> getLostAttackerTroopIds() {
        return lostAttackerTroopIds;
    }

    public void setLostAttackerTroopIds(List<Long> lostAttackerTroopIds) {
        this.lostAttackerTroopIds = lostAttackerTroopIds;
    }

    public List<Long> getLostDefenderTroopIds() {
        return lostDefenderTroopIds;
    }

    public void setLostDefenderTroopIds(List<Long> lostDefenderTroopIds) {
        this.lostDefenderTroopIds = lostDefenderTroopIds;
    }

    public int getAttackerDamageDone() {
        return attackerDamageDone;
    }

    public void setAttackerDamageDone(int attackerDamageDone) {
        this.attackerDamageDone = attackerDamageDone;
    }

    public int getDefenderDamageDone() {
        return defenderDamageDone;
    }

    public void setDefenderDamageDone(int defenderDamageDone) {
        this.defenderDamageDone = defenderDamageDone;
    }

    public long getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(long attackerId) {
        this.attackerId = attackerId;
    }

    public long getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(long defenderId) {
        this.defenderId = defenderId;
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
