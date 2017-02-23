package com.greenfox.jasper.dto;

import java.util.List;

public class KingdomDto {

    private UserDto user;
    private List<BuildingDto> buildings;
    private List<ResourceDto> resources;
    private List<TroopDto> troops;
    private int posX;
    private int posY;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
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

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
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
