package com.greenfox.jasper.dto;

import java.util.List;

public class KingdomDto {

    private SafeUserDto user;
    private List<BuildingDto> buildings;
    private List<ResourceDto> resources;
    private List<TroopDto> troops;
    private long posX;
    private long posY;

    public SafeUserDto getUser() {
        return user;
    }

    public void setUser(SafeUserDto user) {
        this.user = user;
    }

    public List<BuildingDto> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingDto> buildings) {
        this.buildings = buildings;
    }

    public List<ResourceDto> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDto> resources) {
        this.resources = resources;
    }

    public List<TroopDto> getTroops() {
        return troops;
    }

    public void setTroops(List<TroopDto> troops) {
        this.troops = troops;
    }

    public long getPosX() {
        return posX;
    }

    public void setPosX(long posX) {
        this.posX = posX;
    }

    public long getPosY() {
        return posY;
    }

    public void setPosY(long posY) {
        this.posY = posY;
    }

    @Override
    public String toString() {
        return "KingdomDto{" +
                "user=" + user +
                ", buildings=" + buildings +
                ", resources=" + resources +
                ", troops=" + troops +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
