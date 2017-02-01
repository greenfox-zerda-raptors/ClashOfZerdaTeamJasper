package com.greenfox.jasper.responses;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;

@Component
public class BuildingResponse implements Serializable {

    @Autowired
    MainServices mainServices;

    private ArrayList<Building> buildings;

    public ArrayList<Building> getBuildings(long kingdomId){

        buildings = new ArrayList ();

        for(Building building : mainServices.findAllBuildingsByKingdomId(kingdomId)){
            buildings.add(building);
        }
        return buildings;
    }

}
