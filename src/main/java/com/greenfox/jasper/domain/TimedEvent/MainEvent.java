package com.greenfox.jasper.domain.TimedEvent;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "mainevent_table")
@Entity
@Inheritance
public abstract class MainEvent implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mainevent_id")
    private long id;
    @Column(name = "execution_time")
    private long executionTime;
    @Column(name = "was_executed")
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
