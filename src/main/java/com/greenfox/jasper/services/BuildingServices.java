package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.repos.BuildingRepo;
import org.springframework.beans.factory.annotation.Autowired;
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


    public Building findOneBuilding(long buildingId){
        return buildingRepo.findOne(buildingId);
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

    public void addNewBuilding(long kingdomId, String type) {
        if(type.equals("farm") || type.equals("barrack") || type.equals("mine")) {
            Building newBuilding = new Building(type, kingdomServices.findOneById(kingdomId));
            buildingRepo.save(newBuilding);
            timedEventServices.addNewLevelUpEvent(newBuilding.getBuildingId());
        }
    }
    public List<Building> findAllBuildingByKingdomIdAndByType(long kingdomId, String type) {
        return buildingRepo.findAllBuildingByKingdomAndType(kingdomServices.findOneById(kingdomId), type);
    }

    public Building findTownHallByKingdom(Kingdom kingdom){
        return buildingRepo.findAllBuildingByKingdomAndType(kingdom, "townhall").get(0);
    }

    public List<Building> findAllBuildingsByKingdomId(long kingdomId){
        return buildingRepo.findAllByKingdom(kingdomServices.findOneById(kingdomId));
    }

    public int calculateTotalLevel(long kingdomId, String buildingType) {
        return 1;
        // TODO total level
                //buildingRepo.calculateTotalLevel(kingdomServices.findOneById(kingdomId), buildingType);
    }
}
