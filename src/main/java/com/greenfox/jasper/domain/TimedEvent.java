package com.greenfox.jasper.domain;

import javax.persistence.*;

@Entity
@Table(name = "TimedEvents")
public class TimedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // TODO rename (!); rearrange fields
    private long buildingId;
    private boolean wasExecuted = false;
    private long executionTime;

    private GameEvent event;

    public TimedEvent(){

    }

    public TimedEvent(long buildingId, long executionTime, GameEvent event) {
        this.buildingId = buildingId;
        this.executionTime = executionTime;
        this.event = event;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public GameEvent getEvent() {
        return event;
    }

    public void setEvent(GameEvent event) {
        this.event = event;
    }

    public boolean getWasExecuted() {
        return wasExecuted;
    }

    public void setWasExecuted(boolean wasExecuted) {
        this.wasExecuted = wasExecuted;
    }
}
