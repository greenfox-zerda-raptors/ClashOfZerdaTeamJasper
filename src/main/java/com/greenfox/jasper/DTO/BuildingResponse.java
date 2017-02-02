package com.greenfox.jasper.DTO;

import com.greenfox.jasper.domain.Building;

import java.util.ArrayList;

// TODO: rename/ delete it
public class BuildingResponse{

    public BuildingResponse() {
    }

    private ArrayList<Building> buildings = new ArrayList<>();

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }
}
