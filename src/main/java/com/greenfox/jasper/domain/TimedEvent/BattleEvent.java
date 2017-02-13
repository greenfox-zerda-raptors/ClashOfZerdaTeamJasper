package com.greenfox.jasper.domain.TimedEvent;

import com.greenfox.jasper.domain.Troop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import java.util.ArrayList;


@Entity
@Inheritance
public class BattleEvent extends TimedEvent {

    @Column(name = "attacker_id")
    private long attackerKingdomId;
    @Column(name = "troops")
    private ArrayList<Troop> attackingTroops; // decide how we will save troops on the move
    @Column(name = "defender_id")
    private long defenderKingdomId;

    public BattleEvent(){

    }
    public BattleEvent(long executionTime, long attackerKingdomId, ArrayList<Troop> attackingTroops, long defenderKingdomId) {
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

    public ArrayList<Troop> getAttackingTroops() {
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
