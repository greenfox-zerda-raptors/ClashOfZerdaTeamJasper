package com.greenfox.jasper.dto;

/**
 * Created by Zoltán on 2017.02.22..
 */
public class SafeUserDto {
    private long userId;
    private long kingdomId;
    private String kingdomName;
    private int points;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getKingdomId() {
        return kingdomId;
    }

    public void setKingdomId(long kingdomId) {
        this.kingdomId = kingdomId;
    }

    public String getKingdomName() {
        return kingdomName;
    }

    public void setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
