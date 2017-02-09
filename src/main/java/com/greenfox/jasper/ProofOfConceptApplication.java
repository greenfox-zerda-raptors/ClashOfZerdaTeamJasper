package com.greenfox.jasper;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Resource;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProofOfConceptApplication implements CommandLineRunner {

    @Autowired
    KingdomServices kingdomServices;
    @Autowired
    ResourceServices resourceServices;
    @Autowired
    BuildingServices buildingServices;
    @Autowired
    TroopServices troopServices;
    @Autowired
    UserServices userServices;
    @Autowired
    MainEventServices mainEventServices;



    public static void main(String[] args) {
        SpringApplication.run(ProofOfConceptApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        long currentTime = System.currentTimeMillis();
        userServices.saveOneUser(new User("romania", "aaaaa", "admin", "John", "Smith", "some@email.com"));
        resourceServices.saveOneResource(new Resource("food", kingdomServices.findKingdomByName("romania")));
        resourceServices.saveOneResource(new Resource("gold", kingdomServices.findKingdomByName("romania")));
        buildingServices.saveOneBuilding(new Building("farm", kingdomServices.findKingdomByName("romania"), 60));
        buildingServices.saveOneBuilding(new Building("mine", kingdomServices.findKingdomByName("romania"), 60));
        buildingServices.saveOneBuilding(new Building("townhall", kingdomServices.findKingdomByName("romania"), 1));
        buildingServices.saveOneBuilding(new Building("barrack", kingdomServices.findKingdomByName("romania")));
        troopServices.saveOneTroop(new Troop(kingdomServices.findKingdomByName("romania")));
        troopServices.saveOneTroop(new Troop(kingdomServices.findKingdomByName("romania")));
        troopServices.saveOneTroop(new Troop(kingdomServices.findKingdomByName("romania")));

        for(int i = 0; i < 100000; i++){
            mainEventServices.addTestLevelUpEvent(5, currentTime + 60000 + i * 50);
//            mainEventServices.addTestUpgradeTroopEvent(9, 5, currentTime + 30000 + i * 40);
        }
    }
}
