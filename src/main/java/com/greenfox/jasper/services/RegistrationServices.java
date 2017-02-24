package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.Resource;
import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.domain.security.Authority;
import com.greenfox.jasper.repos.UserAuthorityRepo;
import com.greenfox.jasper.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserAuthorityRepo userAuthorityRepo;

    @Autowired
    private KingdomServices kingdomServices;

    @Autowired
    private ResourceServices resourceServices;

    @Autowired
    private BuildingServices buildingServices;

    public void registerNewUser(User user){

        List<Authority> authorities = user.getAuthorities();
        authorities.add(userAuthorityRepo.findOne((long)1));
        userRepo.save(user);
        kingdomServices.saveOneKingdom(user.getKingdom());
        Kingdom newKigndom = kingdomServices.findOneByUserId(user.getId());
        Building townhall = new Building("townhall", kingdomServices.findKingdomByName(user.getKingdom().getName()));
        townhall.levelUp();
        buildingServices.saveOneBuilding(townhall);
        resourceServices.saveOneResource(new Resource("food", kingdomServices.findKingdomByName(user.getKingdom().getName())));
        resourceServices.saveOneResource(new Resource("gold", kingdomServices.findKingdomByName(user.getKingdom().getName())));
        List<Resource> starterResources = resourceServices.findAllResourcesByKingdomId(newKigndom.getKingdomId());
        starterResources.get(0).addResource(500);
        starterResources.get(1).addResource(500);
        resourceServices.saveOneResource(starterResources.get(0));
        resourceServices.saveOneResource(starterResources.get(1));
    }
}
