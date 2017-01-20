package com.greenfox.jasper.states;

import com.greenfox.jasper.model.Building;

/**
 * Created by almasics on 2017.01.20..
 */
public interface BuildingState {
    void executeState(Building building);
}
