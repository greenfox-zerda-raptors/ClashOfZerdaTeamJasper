package com.greenfox.jasper.states;

import com.greenfox.jasper.model.Building;

/**
 * Created by almasics on 2017.01.20..
 */
public class Idle implements BuildingState {

    @Override
    public void executeState(Building building) {
        System.out.println("Building in idle mode");
        building.setBuildingState(new ProductionInProgress());
    }

}