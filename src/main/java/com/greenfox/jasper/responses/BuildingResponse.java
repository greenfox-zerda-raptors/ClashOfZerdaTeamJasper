package com.greenfox.jasper.responses;

import com.greenfox.jasper.domain.Building;

import java.util.ArrayList;

public class BuildingResponse {

    private ArrayList<Building> buildings;

    public BuildingResponse(){
        buildings = new ArrayList<>();
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }
}
