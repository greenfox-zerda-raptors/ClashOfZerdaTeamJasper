//package com.greenfox.jasper.services;
//
//import com.greenfox.jasper.domain.*;
//import com.greenfox.jasper.repos.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
////TODO clean up! divide into several smaller services
//
//@Service
//public class MainServices {
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
//    private BuildingRepo buildingRepo;
//
//    @Autowired
//    private ResourceRepo resourceRepo;
//
//    @Autowired
//    private TroopRepo troopRepo;
//
//    @Autowired
//    private KingdomRepo kingdomRepo;
//
//    @Autowired
//    private TimedEventServices timedEventServices;
//
//
//
//    public User findOneUser(int userId){
//        return userRepo.findOne((long) userId);
//    }
//
//    public Kingdom findOneKingdom(long kingdomId){
//        return kingdomRepo.findOne(kingdomId);
//    }
//
//    public Kingdom findKingdomByName(String kingdom){
//        return kingdomRepo.findByName(kingdom);
//    }
//
//    public Iterable<User> findAllUsers(){
//        return userRepo.findAll();
//    }
//
//    public Building findOneBuilding(int buildingId){
//        return buildingRepo.findOne((long) buildingId);
//    }
//
//    public Iterable<Building> findAllBuildings(){
//        return buildingRepo.findAll();
//    }
//
//    public List<Building> findAllBuildingsByKingdomId(long kingdomId){
//        return buildingRepo.findAllByKingdom(kingdomRepo.findOne(kingdomId));
//    }
//
//    public Troop findOneTroop(int troopId){
//        return troopRepo.findOne((long) troopId);
//    }
//
//    public Iterable<Troop> findAllTroops(){
//        return troopRepo.findAll();
//    }
//
//    public Resource findOneResource(int resourceId){
//        return resourceRepo.findOneById(resourceId);
//    }
//
//
//    public Iterable<Resource> findAllResources(){
//        return resourceRepo.findAll();
//    }
//
//    public List<Troop> findAllTroopsByKingdomId(long kingdomId) {
//        return troopRepo.findAllByKingdom(kingdomRepo.findOne(kingdomId));
//    }
//
//    public void saveOneKingdom(Kingdom kingdom){
//        kingdomRepo.save(kingdom);
//    }
//
//    public void saveOneUser(User user){
//        userRepo.save(user);
//    }
//
//    public void saveOneBuilding(Building building){
//        buildingRepo.save(building);
//    }
//
//    public void saveOneTroop(Troop troop){
//        troopRepo.save(troop);
//    }
//
//    public void saveOneResource(Resource resource){
//        resourceRepo.save(resource);
//    }
//
//    public Resource findAllResourcesByKingdomIdAndType(long kingdomId, String type) {
//        return resourceRepo.findOneByKingdomAndType(findOneKingdom(kingdomId), type);
//    }
//
//    public void addNewBuilding(int kingdomId, String type) {
//        if(type.equals("farm") || type.equals("barrack") || type.equals("mine")) {
//            Building newBuilding = new Building(type, kingdomRepo.findOne((long) kingdomId));
//            buildingRepo.save(newBuilding);
//            timedEventServices.addNewLevelUpEvent(newBuilding.getBuildingId());
//        }
//    }
//
//    public Building findTownHallByKingdom(Kingdom kingdom){
//       return buildingRepo.findAllBuildingByKingdomAndType(kingdom, "townhall").get(0);
//    }
//
//    public List<Building> findAllBuildingByKingdomIdAndByType(int kingdomId, String type) {
//       return buildingRepo.findAllBuildingByKingdomAndType(findOneKingdom((long) kingdomId), type);
//    }
//
//    public List<Resource> findAllResourcesByKingdomId(int kingdomId) {
//        return resourceRepo.findAllByKingdom(kingdomRepo.findOne((long) kingdomId));
//    }
//}
