package com.greenfox.jasper.domain.TimedEvent;

import com.greenfox.jasper.domain.Troop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance
public class BattleEvent extends TimedEvent {

    @Column(name = "attacker_id")
    private long attackerKingdomId;

    @Transient
    @Column(name = "troops_list")
    private List<Troop> attackingTroops; // decide how we will save troops on the move

    @Column(name = "defender_id")
    private long defenderKingdomId;

    public BattleEvent(){

    }
    public BattleEvent(long executionTime, long attackerKingdomId, List<Troop> attackingTroops, long defenderKingdomId) {
        super(executionTime);
        this.attackerKingdomId = attackerKingdomId;
        this.attackingTroops = attackingTroops;
        this.defenderKingdomId = defenderKingdomId;
    }

    public long getAttackerKingdomId() {
        return attackerKingdomId;
    }

    public void setAttackerKingdomId(long attackerKingdomId) {
        this.attackerKingdomId = attackerKingdomId;
    }

    public List<Troop> getAttackingTroops() {
        return attackingTroops;
    }

    public void setAttackingTroops(ArrayList<Troop> attackingTroops) {
        this.attackingTroops = attackingTroops;
    }

    public long getDefenderKingdomId() {
        return defenderKingdomId;
    }

    public void setDefenderKingdomId(long defenderKingdomId) {
        this.defenderKingdomId = defenderKingdomId;
    }



    @Override
    public String toString() {
        return "BattleEvent{" + super.toString() +
                "attackerKingdomId=" + attackerKingdomId +
                ", attackingTroops=" + attackingTroops +
                ", defenderKingdomId=" + defenderKingdomId +
                '}';
    }
}
