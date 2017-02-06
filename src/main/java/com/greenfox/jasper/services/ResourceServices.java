package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.Resource;
import com.greenfox.jasper.domain.Troop;
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
        List<Troop> troops = troopServices.findAllTroopsByKingdomId(kingdomId);

        long dummyTimeForTesting = System.currentTimeMillis() - 60000L;
        //TODO add a real timestamp (eg add a field to kingdom to save the last time someone asked for its resources)

        int changeInFood = changeInResources(foodProductionPerMinute(farmBuildings, townhallBuilding, troops), dummyTimeForTesting);
        int changeInGold = changeInResources(goldProductionPerMinute(mineBuildings, townhallBuilding), dummyTimeForTesting);

        addResource(foodResource, changeInFood);
        addResource(goldResource, changeInGold);

        // TODO a method which updates the timestamp in kingdom, when the last time its resources were calculated
    }

    private int changeInResources(int productionPerMinute, long lastTimeUpdated){
        long ellapsedTime = System.currentTimeMillis()-lastTimeUpdated;
        long ellapsedTimeInMinutes = ellapsedTime/60000L;
        return Math.round(productionPerMinute*ellapsedTimeInMinutes);
    }

    private int foodProductionPerMinute(List<Building> farms, Building townHall, List<Troop> troops){
        return 10*(sumBuildingLevelFromAList(farms) + townHall.getLevel() + troops.size());
    }

    private int goldProductionPerMinute(List<Building> mineBuildings, Building townhallBuilding) {
        return 10*(sumBuildingLevelFromAList(mineBuildings) + townhallBuilding.getLevel());
    }

    private int sumBuildingLevelFromAList(List<Building> buildingList) {
        int sumBuildingLevel = 0;
        for (Building buildingEntity : buildingList) {
            sumBuildingLevel += buildingEntity.getLevel();
        }
        return sumBuildingLevel;
    }

    public void addResource(Resource resource, int amount){
        resource.addResource(amount);
        resourceRepo.save(resource);
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
