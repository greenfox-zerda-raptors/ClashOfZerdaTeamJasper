package com.greenfox.jasper.battle.model;

import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;


@Entity
@Inheritance
@Configurable
public class LevelUpEvent extends MainEvent{
    private long buildingId;

    public LevelUpEvent(){

    }

    public LevelUpEvent(long executionTime, long buildingId) {
        super(executionTime);
        this.buildingId = buildingId;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }



    @Override
    public String toString() {
        return "LevelUpEvent{" +
                super.toString() +
                "buildingId=" + buildingId +
                '}';
    }
}
