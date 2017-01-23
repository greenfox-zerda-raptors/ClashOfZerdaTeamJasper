package com.greenfox.jasper.states;

import com.greenfox.jasper.Models.GameItem.Building;

/**
 * Created by almasics on 2017.01.20..
 */
public class LevelUp implements BuildingState {
//
//    @Override
    public void executeState(Building building) {
//        System.out.println("Upgrading building");
//        long currentTime = System.currentTimeMillis();
//        if (currentTime > building.getEndUpgradeTime()) {
//            upgradeBuildingLevel(building);
//            System.out.println("Building has been updated");
////            building.setBuildingState(new Idle());
//        }
//        System.out.println("building has not been upgraded");
    }

    public void upgradeBuildingLevel(Building building) {
        building.setBuildingLevel(building.getBuildingLevel() + 1);
    }
}
