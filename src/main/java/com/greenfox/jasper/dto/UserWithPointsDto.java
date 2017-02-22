package com.greenfox.jasper.dto;

/**
 * Created by Zoltán on 2017.02.21..
 */


public class UserWithPointsDto {
    private String userName;
    private String kingdomName;
    private int points;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
