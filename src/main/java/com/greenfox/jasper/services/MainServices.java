package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.*;
import com.greenfox.jasper.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class MainServices {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BuildingRepo buildingRepo;

    @Autowired
    ResourceRepo resourceRepo;

    @Autowired
    TroopRepo troopRepo;

    public User findOneUser(int userId){
        return userRepo.findOne((long) userId);
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

    public Troop findOneTroop(int troopId){
        return troopRepo.findOne((long) troopId);
    }

    public Iterable<Troop> findAllTroops(){
        return troopRepo.findAll();
    }

    public Iterable<Resource> findAllResources(){
        return resourceRepo.findAll();
    }

    public void saveOneUser(User user){
        userRepo.save(user);
    }
}
