package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.Resource;
import com.greenfox.jasper.repos.ResourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zoltán on 2017.02.06..
 */

@Service
public class ResourceServices {

    @Autowired
    ResourceRepo resourceRepo;

    @Autowired
    BuildingServices buildingServices;
    @Autowired
    KingdomServices kingdomServices;
    @Autowired
    TroopServices troopServices;


    // TODO refactor!!!!
    public void calculateResource(int kingdomId) {

        Kingdom kingdom = kingdomServices.findOneById(kingdomId);

        Resource foodResource = resourceRepo.findOneByKingdomAndType(kingdom, "food");

        Resource goldResource = resourceRepo.findOneByKingdomAndType(kingdom, "gold");

        List<Building> farmBuildings = buildingServices.findAllBuildingByKingdomIdAndByType(kingdomId, "farm");

        List<Building> mineBuildings = buildingServices.findAllBuildingByKingdomIdAndByType(kingdomId, "mine");

        Building townhallBuilding = buildingServices.findTownHallByKingdom(kingdom);

        long dummyTimeForTesting = System.currentTimeMillis() - 60000L;
        int changeInFood = calulateResourcesToBeAdded(sumBuildingLevelFromAList(farmBuildings) + townhallBuilding.getLevel(), dummyTimeForTesting);
        int changeInGold = calulateResourcesToBeAdded(sumBuildingLevelFromAList(mineBuildings) + townhallBuilding.getLevel(), dummyTimeForTesting);

        foodResource.addResource(changeInFood);
        goldResource.addResource(changeInGold);

        resourceRepo.save(foodResource);
        resourceRepo.save(goldResource);

    }

    private int sumBuildingLevelFromAList(List<Building> buildingList) {
        int sumBuildingLevel = 0;
        for (Building buildingEntity : buildingList) {
            sumBuildingLevel += buildingEntity.getLevel();
        }
        return sumBuildingLevel;
    }

    private int calulateResourcesToBeAdded(int production, long lastTimeOfRequest) {
        long ellapsedTime = System.currentTimeMillis() - lastTimeOfRequest;
        long ellapsedTimeinMinutes = ellapsedTime / 60000L;
        return Math.round(ellapsedTimeinMinutes * production);
    }


    public Resource findOneResource(int resourceId) {
        return resourceRepo.findOneById(resourceId);
    }


    public Iterable<Resource> findAllResources() {
        return resourceRepo.findAll();
    }


    public Resource findAllResourcesByKingdomIdAndType(long kingdomId, String type) {
        return resourceRepo.findOneByKingdomAndType(kingdomServices.findOneById(kingdomId), type);
    }

    public List<Resource> findAllResourcesByKingdomId(int kingdomId) {
        return resourceRepo.findAllByKingdom(kingdomServices.findOneById((long) kingdomId));
    }

    public List<Building> findAllBuildingByKingdomIdAndByType(int kingdomId, String mine) {
        return buildingServices.findAllBuildingByKingdomIdAndByType(kingdomId, mine);
    }

    public void saveOneResource(Resource resource){
        resourceRepo.save(resource);
    }
}
