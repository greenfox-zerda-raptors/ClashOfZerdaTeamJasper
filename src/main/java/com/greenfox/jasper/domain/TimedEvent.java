package com.greenfox.jasper.domain;

import javax.persistence.*;

@Entity
@Table(name = "TimedEvents")
public class TimedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // TODO rearrange fields for battle => troops/army id & defender id is needed to resolve who will fight against who with what => some further work on troop/army is required
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

    public boolean wasExecuted() {
        return wasExecuted;
    }

    public void setWasExecuted(boolean wasExecuted) {
        this.wasExecuted = wasExecuted;
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
}
