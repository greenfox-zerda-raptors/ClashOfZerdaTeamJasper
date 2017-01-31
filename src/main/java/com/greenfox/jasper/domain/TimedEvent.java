package com.greenfox.jasper.domain;

import javax.persistence.*;

@Entity
@Table(name = "timed_event_table")
public class TimedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timed_event_id")
    private long id;

    private long buildingId;
    // TODO rearrange fields for battle => troops/army id & defender id is needed to resolve who will fight against who with what => some further work on troop/army is required
    // TODO unit upgrade needs an additional troop ID (can be solved with the todo above);
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

    public boolean isWasExecuted() {
        return wasExecuted;
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
