package com.greenfox.jasper.domain.TimedEvent;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "timed_event_table")
@Entity
@Inheritance
public abstract class TimedEvent implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timed_event_id")
    private long id;
    @Column(name = "execution_time")
    private long executionTime;
    @Column(name = "was_executed")
    private int wasExecuted;

    public TimedEvent(){

    }

    public TimedEvent(long executionTime){
        this.wasExecuted = 0;
        this.executionTime = executionTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public int getWasExecuted() {
        return wasExecuted;
    }

    public void setWasExecuted(int wasExecuted) {
        this.wasExecuted = wasExecuted;
    }

    @Override
    public String toString() {
        return "TimedEvent{" +
                "id=" + id +
                ", executionTime=" + executionTime +
                ", wasExecuted=" + wasExecuted +
                '}';
    }
}
