package com.greenfox.jasper.services;


import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.repos.KingdomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomServices {

    @Autowired
    KingdomRepo kingdomRepo;

    public Kingdom findOneById(long kingdomId){
        return kingdomRepo.findOne(kingdomId);
    }

    public void saveOneKingdom(Kingdom kingdom){
        kingdomRepo.save(kingdom);
    }

    public Kingdom findOneKingdom(long kingdomId){
        return kingdomRepo.findOne(kingdomId);
    }

    public Kingdom findKingdomByName(String kingdom){
        return kingdomRepo.findByName(kingdom);
    }




}
