package com.greenfox.jasper.dto;

import java.util.ArrayList;
import java.util.List;

public class BuildingResponse{

    private List<BuildingDto> buildings = new ArrayList<>();

    public BuildingResponse() {
    }

    public BuildingResponse(List<BuildingDto> buildings) {
        this.buildings = buildings;
    }

    public List<BuildingDto> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingDto> buildings) {
        this.buildings = buildings;
    }

}