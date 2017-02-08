package com.greenfox.jasper.domain.TimedEvent;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "timedEventSuperclass")
@Entity
@Inheritance
public abstract class MainEvent implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long executionTime;
    private boolean wasExecuted;

    public MainEvent(){

    }

    public MainEvent(long executionTime){
        this.wasExecuted = false;
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

    public boolean isWasExecuted() {
        return wasExecuted;
    }

    public void setWasExecuted(boolean wasExecuted) {
        this.wasExecuted = wasExecuted;
    }


    @Override
    public String toString() {
        return "MainEvent{" +
                "id=" + id +
                ", executionTime=" + executionTime +
                ", wasExecuted=" + wasExecuted +
                '}';
    }
}
