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

    // TODO refactor! + consider adding a production field into kingdom/resource entity to make this method execution faster
    public void calculateResource(long kingdomId) {

        Kingdom kingdom = kingdomServices.findOneById(kingdomId);

        Resource foodResource = resourceRepo.findOneByKingdomAndType(kingdom, "food");
        Resource goldResource = resourceRepo.findOneByKingdomAndType(kingdom, "gold");

        List<Building> farmBuildings = buildingServices.findAllBuildingByKingdomIdAndByType(kingdomId, "farm");
        List<Building> mineBuildings = buildingServices.findAllBuildingByKingdomIdAndByType(kingdomId, "mine");
        Building townhallBuilding = buildingServices.findTownHallByKingdom(kingdom);
        List<Troop> troops = troopServices.findAllTroopsByKingdomId(kingdomId);

        long kingdomLastUpdateTime = kingdom.getUpdateTime();

        float changeInFood = changeInResources(
                foodProductionPerMinute(
                        farmBuildings,
                        townhallBuilding,
                        troops),
                kingdomLastUpdateTime);
        float changeInGold = changeInResources(
                goldProductionPerMinute(
                        mineBuildings,
                        townhallBuilding),
                kingdomLastUpdateTime);

        addResource(foodResource, changeInFood);
        addResource(goldResource, changeInGold);
        kingdom.setUpdateTime(System.currentTimeMillis());
        kingdomServices.saveOneKingdom(kingdom);

    }

    private float changeInResources(int productionPerMinute, long lastTimeUpdated){
        long ellapsedTime = System.currentTimeMillis()-lastTimeUpdated;
        float ellapsedTimeInMinutes = ellapsedTime/60000f;
        return productionPerMinute*ellapsedTimeInMinutes;
    }

    private int foodProductionPerMinute(List<Building> farms, Building townHall, List<Troop> troops){
        return 10*(sumBuildingLevelFromAList(farms)
                + townHall.getLevel() +
                troops.size());
    }

    private int goldProductionPerMinute(List<Building> mines, Building townhallBuilding) {
        return 10*(sumBuildingLevelFromAList(mines)
                + townhallBuilding.getLevel());
    }

    private int sumBuildingLevelFromAList(List<Building> buildingList) {
        int sumBuildingLevel = 0;
        for (Building buildingEntity : buildingList) {
            sumBuildingLevel += buildingEntity.getLevel();
        }
        return sumBuildingLevel;
    }

    public void addResource(Resource resource, float amount){
        resource.addResource(amount);
        resourceRepo.save(resource);
    }

    public Resource findOneResource(long resourceId) {
        return resourceRepo.findOneById(resourceId);
    }

    public Iterable<Resource> findAllResources() {
        return resourceRepo.findAll();
    }

    public Resource findAllResourcesByKingdomIdAndType(long kingdomId, String type) {
        return resourceRepo.findOneByKingdomAndType(kingdomServices.findOneById(kingdomId), type);
    }

    public List<Resource> findAllResourcesByKingdomId(long kingdomId) {
        return resourceRepo.findAllByKingdom(kingdomServices.findOneById(kingdomId));

    }

    public Resource findAllGoldResourceByKingdomId(long kingdomId){
        return resourceRepo.findOneByKingdomAndType(kingdomServices.findOneById(kingdomId), "gold");
    }

    public List<Resource> findAllResourcesByKingdomId(int kingdomId) {
        return resourceRepo.findAllByKingdom(kingdomServices.findOneById((long) kingdomId));
    }

    public List<Building> findAllBuildingByKingdomIdAndByType(long kingdomId, String mine) {
        return buildingServices.findAllBuildingByKingdomIdAndByType(kingdomId, mine);
    }

    public void saveOneResource(Resource resource){
        resourceRepo.save(resource);
    }

    public boolean levelUpBuildingMoneyCheck(long kingdomId, long buildingId){
        Resource gold = findAllGoldResourceByKingdomId(kingdomId);
        float money;
        money = gold.getAmount();
        float cost;
        Building building = buildingServices.findOneBuilding(buildingId);
        cost = (building.getLevel()+1)*100;
        if (money>cost){
            money -= cost;
            gold.setAmount(money);
            resourceRepo.save(gold);
        }
        boolean result;
        result=money>cost;
        return result;
    }

    public boolean buyNewBuilding(long kingdomId){
        Resource gold = findAllGoldResourceByKingdomId(kingdomId);
        float money;
        money = gold.getAmount();
        boolean result;
        result=(money>=250);
        if(result){
            money -= 250;
            gold.setAmount(money);
            resourceRepo.save(gold);
        }
        return result;
    }

}
