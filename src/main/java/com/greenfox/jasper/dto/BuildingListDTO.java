package com.greenfox.jasper.dto;

import java.util.ArrayList;
import java.util.List;

public class BuildingListDTO {

    private List<BuildingDto> buildings = new ArrayList<>();

    public BuildingListDTO() {
    }

    public BuildingListDTO(List<BuildingDto> buildings) {
        this.buildings = buildings;
    }

    public List<BuildingDto> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingDto> buildings) {
        this.buildings = buildings;
    }

}
