package com.greenfox.jasper.dto;

/**
 * Created by Zoltán on 2017.02.13..
 */
public class TroopPostDto {
    private long troopId;
    private long barrackId;

    public long getTroopId() {
        return troopId;
    }

    public void setTroopId(long troopId) {
        this.troopId = troopId;
    }

    public long getBarrackId() {
        return barrackId;
    }

    public void setBarrackId(long barrackId) {
        this.barrackId = barrackId;
    }
}
