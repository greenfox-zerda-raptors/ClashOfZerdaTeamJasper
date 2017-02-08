package com.greenfox.jasper.domain;

import javax.persistence.*;
import java.util.ArrayList;


@Entity
@Table(name = "Battles")
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long battleId;
    private long attackerId;
    private ArrayList<Long> attackerTroops;
    private long defenderId;
    private ArrayList<Long> defenderTroops;
    private BattleResult battleStatus;
    private ArrayList<String> battleMessages;

    public Battle() {
    }

    public Battle(long attackerId, ArrayList<Long> attackerTroops, long defenderId, ArrayList<Long> defenderTroops) {
        this.attackerId = attackerId;
        this.attackerTroops = attackerTroops;
        this.defenderId = defenderId;
        this.defenderTroops = defenderTroops;
    }

    public void resolveBattle(){

    }

    public long getBattleId() {
        return battleId;
    }

    public void setBattleId(long battleId) {
        this.battleId = battleId;
    }

    public long getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(long attackerId) {
        this.attackerId = attackerId;
    }

    public ArrayList<Long> getAttackerTroops() {
        return attackerTroops;
    }

    public void setAttackerTroops(ArrayList<Long> attackerTroops) {
        this.attackerTroops = attackerTroops;
    }

    public long getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(long defenderId) {
        this.defenderId = defenderId;
    }

    public ArrayList<Long> getDefenderTroops() {
        return defenderTroops;
    }

    public void setDefenderTroops(ArrayList<Long> defenderTroops) {
        this.defenderTroops = defenderTroops;
    }

    public BattleResult getBattleStatus() {
        return battleStatus;
    }

    public void setBattleStatus(BattleResult battleStatus) {
        this.battleStatus = battleStatus;
    }

    public ArrayList<String> getBattleMessages() {
        return battleMessages;
    }

    public void setBattleMessages(ArrayList<String> battleMessages) {
        this.battleMessages = battleMessages;
    }
}
