package com.greenfox.jasper.services;


import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.dto.LocationDto;
import com.greenfox.jasper.repos.KingdomRepo;
import com.greenfox.jasper.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KingdomServices {

    @Autowired
    KingdomRepo kingdomRepo;

    public Kingdom findOneById(long kingdomId) {
        return kingdomRepo.findOne(kingdomId);
    }

    public void saveOneKingdom(Kingdom kingdom) {
        kingdomRepo.save(kingdom);
    }

    public Kingdom findOneKingdom(long kingdomId) {
        return kingdomRepo.findOne(kingdomId);
    }

    public Kingdom findKingdomByName(String kingdom) {
        return kingdomRepo.findByName(kingdom);
    }

    public List<Kingdom> findAll() {
        return kingdomRepo.findAll();
    }

    public Kingdom findOneByUserId(long userId){
        return kingdomRepo.findOneByUserId(userId);
    }


    public void updateLocation(JwtUser currentUser, LocationDto locationDto) {
        Kingdom movingKingdom = kingdomRepo.findOneByUserId(currentUser.getId());

    }

    public long getKingdomIdFromJWTUser(JwtUser currentUser) {
        return findOneByUserId(currentUser.getId()).getKingdomId();
    }
}
