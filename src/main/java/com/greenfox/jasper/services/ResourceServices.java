package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.Resource;
import com.greenfox.jasper.repos.BuildingRepo;
import com.greenfox.jasper.repos.KingdomRepo;
import com.greenfox.jasper.repos.ResourceRepo;
import com.greenfox.jasper.repos.TroopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zoltán on 2017.02.06..
 */

@Service
public class ResourceServices {

    BuildingRepo buildingRepo;

    ResourceRepo resourceRepo;

    KingdomRepo kingdomRepo;

    TroopRepo troopRepo;

    MainServices mainServices;


    @Autowired
    public ResourceServices(MainServices mainServices, BuildingRepo buildingRepo, ResourceRepo resourceRepo, KingdomRepo kingdomRepo, TroopRepo troopRepo) {
        this.mainServices = mainServices;
        this.buildingRepo = buildingRepo;
        this.resourceRepo = resourceRepo;
        this.kingdomRepo = kingdomRepo;
        this.troopRepo = troopRepo;
    }



    // TODO refactor!!!!
    public void calculateResource(long kingdomId){

        Kingdom kingdom = kingdomRepo.findOne(kingdomId);

        Resource foodResource = resourceRepo.findOneByKingdomAndType(kingdom, "food");

        Resource goldResource = resourceRepo.findOneByKingdomAndType(kingdom, "gold");

        List<Building> farmBuildings = buildingRepo.findAllBuildingByKingdomAndType(kingdom, "farm");

        List<Building> mineBuildings = buildingRepo.findAllBuildingByKingdomAndType(kingdom, "mine");

        Building townhallBuilding = mainServices.findTownHallByKingdom(kingdom);

        long dummyTimeForTesting = System.currentTimeMillis() - 60000L;
        int changeInFood = calulateResourcesToBeAdded(sumBuildingLevelFromAList(farmBuildings) + townhallBuilding.getLevel(), dummyTimeForTesting);
        int changeInGold = calulateResourcesToBeAdded(sumBuildingLevelFromAList(mineBuildings) + townhallBuilding.getLevel(), dummyTimeForTesting);

        foodResource.addResource(changeInFood);
        goldResource.addResource(changeInGold);

        resourceRepo.save(foodResource);
        resourceRepo.save(goldResource);

    }

    private int sumBuildingLevelFromAList(List<Building> buildingList){
        int sumBuildingLevel = 0;
        for (Building buildingEntity : buildingList) {
            sumBuildingLevel += buildingEntity.getLevel();
        }
        return sumBuildingLevel;
    }

    private int calulateResourcesToBeAdded(int production, long lastTimeOfRequest){
        long ellapsedTime = System.currentTimeMillis() - lastTimeOfRequest;
        long ellapsedTimeinMinutes = ellapsedTime / 60000L;
        return Math.round(ellapsedTimeinMinutes*production);
    }



}
