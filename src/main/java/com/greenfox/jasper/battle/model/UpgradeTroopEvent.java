package com.greenfox.jasper.battle.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;


@Entity
@Inheritance
public class UpgradeTroopEvent extends MainEvent {
    private long buildingId;
    private long troopId;

    public UpgradeTroopEvent(){

    }

    public UpgradeTroopEvent(long executionTime, long buildingId, long troopId) {
        super(executionTime);
        this.buildingId = buildingId;
        this.troopId = troopId;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
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
                "buildingId=" + buildingId +
                ", troopId=" + troopId +
                '}';
    }
}
