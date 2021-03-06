package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.repos.TroopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TroopServices {

    @Autowired
    TroopRepo troopRepo;

    @Autowired
    KingdomServices kingdomServices;

    @Autowired
    TimedEventServices timedEventServices;

    public Troop findOneTroop(long troopId){
        return troopRepo.findOne(troopId);
    }

    public Iterable<Troop> findAllTroops() {
        return troopRepo.findAll();
    }

    public List<Troop> findAllTroopsByKingdomId(long kingdomId) {
        return troopRepo.findAllByKingdom(kingdomServices.findOneById(kingdomId));
    }

    public List<Troop> findAllTroopsByKingdomName(String name) {
        return troopRepo.findAllByKingdom(kingdomServices.findKingdomByName(name));
    }

    public void saveOneTroop(Troop troop) {
        troopRepo.save(troop);
    }

    public void saveAllFromList(List<Troop> listOfTroops){
        troopRepo.save(listOfTroops);
    }
    public void deleteOneTroop(Troop troop) {
        troopRepo.delete(troop);
    }

    public void deleteAllFromList(List<Long> listOfTroops){
        for(Long id : listOfTroops){
            troopRepo.delete(id);
            }
    }

    public void deleteMany(List<Troop> ... Lists){
        for(List<Troop> troops : Lists){
            troopRepo.delete(troops);
            }
    }

    public void deleteTest(long troopId) {
        troopRepo.delete(troopId);
    }

    public void addNewTroop(long kingdomId) {
        Troop newTroop = new Troop(kingdomServices.findOneById(kingdomId));
        troopRepo.save(newTroop);
        timedEventServices.addNewUpgradeTroopEvent(newTroop.getTroopId(), kingdomId);
    }
}
