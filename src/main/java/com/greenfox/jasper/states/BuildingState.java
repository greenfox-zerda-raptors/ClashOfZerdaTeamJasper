package com.greenfox.jasper.states;

import com.greenfox.jasper.Models.GameItem.Building;

/**
 * Created by almasics on 2017.01.20..
 */
public interface BuildingState {
    void executeState(Building building);
}
