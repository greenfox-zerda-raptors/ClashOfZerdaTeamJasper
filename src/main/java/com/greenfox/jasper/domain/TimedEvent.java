package com.greenfox.jasper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "timed_event_table")
public class TimedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timed_event_id")
    private long id;

    @ManyToOne(targetEntity = Building.class)
    @JsonIgnore
    private long buildingId;

    @Column(name = "was_executed")
    private boolean wasExecuted = false;

    @Column(name = "execution_time")
    private long executionTime;

    @Column(name = "events")
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
