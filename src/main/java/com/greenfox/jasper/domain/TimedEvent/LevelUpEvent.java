package com.greenfox.jasper.domain.TimedEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;


@Entity
@Inheritance
public class LevelUpEvent extends TimedEvent {
    @Column(name = "building_id")
    private long buildingId;
    @Column(name = "kingdom_id")
    private long kingdomId;
    public LevelUpEvent(){

    }

    public LevelUpEvent(long executionTime, long kingdomId, long buildingId) {
        super(executionTime);
        this.buildingId = buildingId;
        this.kingdomId = kingdomId;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public long getKingdomId() {
        return kingdomId;
    }

    public void setKingdomId(long kingdomId) {
        this.kingdomId = kingdomId;
    }
}
