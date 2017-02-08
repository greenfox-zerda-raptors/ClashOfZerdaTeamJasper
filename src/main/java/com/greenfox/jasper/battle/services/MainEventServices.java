package com.greenfox.jasper.battle.services;

import com.greenfox.jasper.battle.model.BattleEvent;
import com.greenfox.jasper.battle.model.LevelUpEvent;
import com.greenfox.jasper.battle.model.MainEvent;
import com.greenfox.jasper.battle.model.UpgradeTroopEvent;
import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MainEventServices {

    @Autowired
    MainEventRepo mainEventRepo;


    public void addNewLvlUpEvent(long buildingId){
        LevelUpEvent sajt = new LevelUpEvent(System.currentTimeMillis()+15000, buildingId);
        mainEventRepo.save(sajt);
    }

    public void addNewBattleEvent(long attackerId, ArrayList<Troop> troops, long defenderId){
        BattleEvent sajt = new BattleEvent(System.currentTimeMillis()+15000, attackerId, troops, defenderId);
        mainEventRepo.save(sajt);
    }

    public void addNewUpgradeTroopEvenet(long troopId, long barrackId){
        UpgradeTroopEvent sajt = new UpgradeTroopEvent(System.currentTimeMillis()+15000, barrackId, troopId);
        mainEventRepo.save(sajt);
    }

    @Scheduled(fixedRate = 1000)
    public void checkForEvents(){
        long currentTime = System.currentTimeMillis();

        List<MainEvent> listedEvents = mainEventRepo.findAllWaitingForExecution(currentTime);

        for (MainEvent listedEvent : listedEvents) {
            processEventTest(listedEvent);
        }
        System.out.println("I finished processing the events in: " + (System.currentTimeMillis() - currentTime));
    }


    private void processEventTest(MainEvent mainEvent){
       if(mainEvent.getClass() == BattleEvent.class){
           executeBattle(mainEvent);
       }else if(mainEvent.getClass() == LevelUpEvent.class){
           executeLevelUp(mainEvent);
       }else if(mainEvent.getClass() == UpgradeTroopEvent.class){
           executeTroopUpgrade(mainEvent);
       }else{
           System.out.println("No such event method");
       }
        mainEvent.setWasExecuted(true);
        mainEventRepo.save(mainEvent);
    }


    public List<MainEvent> findAll(){
        return mainEventRepo.findAll();
    }


    public void executeBattle(MainEvent mainEvent) {
        // TODO actually doing battle
        BattleEvent battleEvent = (BattleEvent) mainEvent;
        System.out.println("Wohohohohooooooo");
    }
    private void executeLevelUp(MainEvent mainEvent) {
        LevelUpEvent levelUpEvent = (LevelUpEvent) mainEvent;
        System.out.println("executing the lvl up method");
        Building tempBuilding = mainServices.findOneBuilding((int) levelUpEvent.getBuildingId());
        tempBuilding.levelUp();
        mainServices.saveOneBuilding(tempBuilding);
    }

    private void executeTroopUpgrade(MainEvent mainEvent) {
        UpgradeTroopEvent upgradeTroopEvent = (UpgradeTroopEvent) mainEvent;
        Troop troopToBeUpgraded = mainServices.findOneTroop((int) upgradeTroopEvent.getTroopId());
        troopToBeUpgraded.upgrade(); // this needs to be done
        mainServices.saveOneTroop(troopToBeUpgraded);
        System.out.println("Upgraded your troop");
    }
}
