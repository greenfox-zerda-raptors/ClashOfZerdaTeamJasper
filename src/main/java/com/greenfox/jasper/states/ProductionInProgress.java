package com.greenfox.jasper.states;

import com.greenfox.jasper.model.Building;

/**
 * Created by almasics on 2017.01.20..
 */
public class ProductionInProgress implements BuildingState {

    @Override
    public void executeState(Building building) {
        long currenttime = System.currentTimeMillis();
        System.out.println("Building production started");
//        building.setBuildingState(new Idle());
    }

}
