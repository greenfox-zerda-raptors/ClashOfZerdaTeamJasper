package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.*;
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

    public Troop findOneTroop(long troopId){
        return troopRepo.findOne(troopId);
    }

    public Iterable<Troop> findAllTroops(){
        return troopRepo.findAll();
    }

    public List<Troop> findAllTroopsByKingdomId(long kingdomId) {
        return troopRepo.findAllByKingdom(kingdomServices.findOneById(kingdomId));
    }

    public void saveOneTroop(Troop troop){
        troopRepo.save(troop);
    }


}
