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

    @Autowired
    public ResourceServices(BuildingRepo buildingRepo, ResourceRepo resourceRepo, KingdomRepo kingdomRepo, TroopRepo troopRepo) {
        this.buildingRepo = buildingRepo;
        this.resourceRepo = resourceRepo;
        this.kingdomRepo = kingdomRepo;
        this.troopRepo = troopRepo;
    }

    public void calculateResource(long kingdomId){

        Kingdom kingdom = kingdomRepo.findOne(kingdomId);

        Resource resource = resourceRepo.findOneByKingdomAndType(kingdom, "food");

        List<Building> foodBuildings = buildingRepo.findAllBuildingByKingdomAndType(kingdom, "food");

        List<Building> goldBuildings = buildingRepo.findAllBuildingByKingdomAndType(kingdom, "food");

        long dummyTimeForTesting = System.currentTimeMillis() - 60000;
        int changeInFood =  calulateResourcesToBeAdded(sumBuildingLevelFromAList(foodBuildings), dummyTimeForTesting);
        int changeInGold = calulateResourcesToBeAdded(sumBuildingLevelFromAList(goldBuildings), dummyTimeForTesting);



    }

    private int sumBuildingLevelFromAList(List<Building> buildingList){
        int sumBuildingLevel = 0;
        for(int i = 0; i < buildingList.size(); i++){

        }
        return sumBuildingLevel;
    }

    private int calulateResourcesToBeAdded(int production, long lastTimeOfRequest){
        long ellapsedTime = System.currentTimeMillis() - lastTimeOfRequest;

        long ellapsedTimeInSeconds = ellapsedTime / 1000;

        return Math.toIntExact(ellapsedTimeInSeconds*production);
    }



}
