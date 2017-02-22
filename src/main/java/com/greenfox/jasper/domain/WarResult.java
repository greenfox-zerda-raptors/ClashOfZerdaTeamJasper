package com.greenfox.jasper.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "war_result_table")
public class WarResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "war_result_id")
    private long warResultId;
    @Transient
    private List<Long> defenderLostBuildingIds;
    @Transient
    private  List<Long> attackerTroopIds;
    @Transient
    private List<Long> defenderTroopIds;
    @Transient
    private List<Long> lostAttackerTroopIds;
    @Transient
    private List<Long> lostDefenderTroopIds;
    @Column(name = "attacker_damage_done")
    private int attackerDamageDone;
    @Column(name = "defender_damage_done")
    private int defenderDamageDone;
    @Column(name = "atacker_id")
    private long attackerId;
    @Column(name = "defender_id")
    private long defenderId;


    public WarResult(
            List<Long> defenderLostBuildingIds,
            List<Long> attackerTroopIds,
            List<Long> defenderTroopIds,
            List<Long> lostAttackerTroopIds,
            List<Long> lostDefenderTroopIds,
            int attackerDamageDone,
            int defenderDamageDone,
            long attackerId,
            long defenderId) {

        this.defenderLostBuildingIds = defenderLostBuildingIds;
        this.attackerTroopIds = attackerTroopIds;
        this.defenderTroopIds = defenderTroopIds;
        this.lostAttackerTroopIds = lostAttackerTroopIds;
        this.lostDefenderTroopIds = lostDefenderTroopIds;
        this.attackerDamageDone = attackerDamageDone;
        this.defenderDamageDone = defenderDamageDone;
        this.attackerId = attackerId;
        this.defenderId = defenderId;
    }

    public long getWarResultId() {
        return warResultId;
    }

    public void setWarResultId(long warResultId) {
        this.warResultId = warResultId;
    }

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
}
