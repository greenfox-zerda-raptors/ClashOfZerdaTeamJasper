package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.*;
import com.greenfox.jasper.repos.BuildingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BuildingServices {

    @Autowired
    BuildingRepo buildingRepo;

    @Autowired
    TimedEventServices timedEventServices;

    @Autowired
    KingdomServices kingdomServices;


    public Building findOneBuilding(int buildingId){
        return buildingRepo.findOne((long) buildingId);
    }

    public Building findLastBuilding(long kingdomId){
        return buildingRepo.findAllOrderByBuildingIdDesc(kingdomServices.findOneById(kingdomId)).get(0);
    }

    public List<Building> findAllBuildings(){
        return buildingRepo.findAll();
    }

    public void saveOneBuilding(Building building){
        buildingRepo.save(building);
    }

    public void addNewBuilding(int kingdomId, String type) {
        if(type.equals("farm") || type.equals("barrack") || type.equals("mine")) {
            Building newBuilding = new Building(type, kingdomServices.findOneById((long) kingdomId));
            buildingRepo.save(newBuilding);
            timedEventServices.addNewLevelUpEvent(newBuilding.getBuildingId());
        }
    }
    public List<Building> findAllBuildingByKingdomIdAndByType(int kingdomId, String type) {
        return buildingRepo.findAllBuildingByKingdomAndType(kingdomServices.findOneById((long) kingdomId), type);
    }

    public Building findTownHallByKingdom(Kingdom kingdom){
        return buildingRepo.findAllBuildingByKingdomAndType(kingdom, "townhall").get(0);
    }

    public List<Building> findAllBuildingsByKingdomId(long kingdomId){
        return buildingRepo.findAllByKingdom(kingdomServices.findOneById(kingdomId));
    }

}
