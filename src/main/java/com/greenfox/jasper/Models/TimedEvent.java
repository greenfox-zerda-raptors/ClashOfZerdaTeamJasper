package com.greenfox.jasper.Models;

import javax.persistence.*;

/**
 * Created by Zoltán on 2017.01.22..
 */
@Entity
@Table(name = "TimedEvents")
public class TimedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long buildingId;
    private boolean wasExecuted = false;
    private long executionTime;

    private AvailableEvent event;

    public TimedEvent(){

    }

    public TimedEvent(long buildingId, long executionTime, AvailableEvent event){
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

    public AvailableEvent getEvent() {
        return event;
    }

    public void setEvent(AvailableEvent event) {
        this.event = event;
    }

    public boolean isWasExecuted() {
        return wasExecuted;
    }

    public void setWasExecuted(boolean wasExecuted) {
        this.wasExecuted = wasExecuted;
    }
}
