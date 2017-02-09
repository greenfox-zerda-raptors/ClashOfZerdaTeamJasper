package com.greenfox.jasper.domain.TimedEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
@Inheritance
public class TrainTroopEvent extends TimedEvent {
//    @OneToOne(mappedBy = "building", cascade = CascadeType.ALL)
    @Column(name = "building_id")
    private long buildingId;

    public TrainTroopEvent(long executionTime, long buildingId) {
        super(executionTime);
        this.buildingId = buildingId;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }
}
