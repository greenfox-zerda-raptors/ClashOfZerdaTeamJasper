package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.*;
import com.greenfox.jasper.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BuildingRepo buildingRepo;

    @Autowired
    private ResourceRepo resourceRepo;

    @Autowired
    private TroopRepo troopRepo;

    @Autowired
    private KingdomRepo kingdomRepo;

    @Autowired
    private EventServices eventServices;


    public User findOneUser(int userId){
        return userRepo.findOne((long) userId);
    }

    public Kingdom findOneKingdom(long kingdomId){
        return kingdomRepo.findOne(kingdomId);
    }

    public Kingdom findKingdomByName(String kingdom){
        return kingdomRepo.findByName(kingdom);
    }

    public Iterable<User> findAllUsers(){
        return userRepo.findAll();
    }

    public Building findOneBuilding(int buildingId){
        return buildingRepo.findOne((long) buildingId);
    }

    public Iterable<Building> findAllBuildings(){
        return buildingRepo.findAll();
    }

    public Iterable<Building> findAllBuildingsByKingdomId(long kingdomId){
        return buildingRepo.findAllByKingdom(kingdomRepo.findOne(kingdomId));
    }

    public Troop findOneTroop(int troopId){
        return troopRepo.findOne((long) troopId);
    }

    public Iterable<Troop> findAllTroops(){
        return troopRepo.findAll();
    }

    public Resource findOneResource(String resourceName){
        return resourceRepo.findOne(resourceName);
    }

    public Iterable<Resource> findAllResources(){
        return resourceRepo.findAll();
    }

    public Iterable<Troop> findAllTroopsByKingdomId(long kingdomId) {
        return troopRepo.findAllByKingdom(kingdomRepo.findOne(kingdomId));
    }

    public void saveOneKingdom(Kingdom kingdom){
        kingdomRepo.save(kingdom);
    }

    public void saveOneUser(User user){
        userRepo.save(user);
    }

    public void saveOneBuilding(Building building){
        buildingRepo.save(building);
    }

    public void saveOneTroop(Troop troop){
        troopRepo.save(troop);
    }

    public void saveOneResource(Resource resource){
        resourceRepo.save(resource);
    }

    public Iterable<Resource> findAllResourcesByKingdomId(long kingdomId) {
        return resourceRepo.findAllByKingdom(kingdomRepo.findOne(kingdomId));
    }

    public void addNewBuilding(int userId, String type) {
       Building newBuilding =  new Building(type, userRepo.findOne((long) userId).getKingdom());
        buildingRepo.save(newBuilding);
        eventServices.addNewLevelUpEvent(newBuilding.getBuildingId());
    }
}
