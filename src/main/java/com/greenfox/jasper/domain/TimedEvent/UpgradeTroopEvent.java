package com.greenfox.jasper.domain.TimedEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;


@Entity
@Inheritance
public class UpgradeTroopEvent extends TimedEvent {
    @Column(name = "kingdom_id")
    private long kingdomId;
    @Column(name = "troop_id")
    private long troopId;

    public UpgradeTroopEvent(){

    }

    public UpgradeTroopEvent(long executionTime, long kingdomId, long troopId) {
        super(executionTime);
        this.kingdomId = kingdomId;
        this.troopId = troopId;
    }

    public long getKingdomId() {
        return kingdomId;
    }

    public void setKingdomId(long kingdomId) {
        this.kingdomId = kingdomId;
    }

    public long getTroopId() {
        return troopId;
    }

    public void setTroopId(long troopId) {
        this.troopId = troopId;
    }

    @Override
    public String toString() {
        return "UpgradeTroopEvent{" + super.toString() +
                "kingdomId=" + kingdomId +
                ", troopId=" + troopId +
                '}';
    }
}
