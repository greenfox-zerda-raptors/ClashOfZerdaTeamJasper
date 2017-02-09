package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.TimedEvent.*;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.repos.MainEventRepo;
import com.greenfox.jasper.repos.TrainTroopEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MainEventServices {

    @Autowired
    MainEventRepo mainEventRepo;

    @Autowired
    TrainTroopEventRepo trainTroopEventRepo;

    @Autowired
    BuildingServices buildingServices;

    @Autowired
    TroopServices troopServices;

    @Autowired
    KingdomServices kingdomServices;

    @Autowired
    ResourceServices resourceServices;


    // @Inheritance, mainEventRepo will obtain everything you need, "subrepos" will only obtain the data for that class (no data from superclass only ID)
    // If you want to filter in different event types, there is a field automatically generated, use custom queries for them


    @Scheduled(fixedRate = 1000)
    public void checkForEvents(){
        long currentTime = System.currentTimeMillis();

        List<MainEvent> listedEvents = mainEventRepo.findAllWaitingForExecution(currentTime);
        if(listedEvents.size() != 0) {
            for (MainEvent listedEvent : listedEvents) {
                processEventTest(listedEvent);
            }
        }
        System.out.println("I finished processing the events in: " + (System.currentTimeMillis() - currentTime));
    }


    private void processEventTest(MainEvent mainEvent){

       if(isBattleEvent(mainEvent)){
           executeBattle(mainEvent);
       }else if(isLevelUpEvent(mainEvent)){
           executeLevelUp(mainEvent);
       }else if(isUpgradeTroopEvent(mainEvent)){
           executeTroopUpgrade(mainEvent);
       }else{
           System.out.println("No such event-method");
       }
        mainEvent.setWasExecuted(true);
        mainEventRepo.save(mainEvent);
    }

    private boolean isUpgradeTroopEvent(MainEvent mainEvent) {
        return mainEvent.getClass() == UpgradeTroopEvent.class;
    }

    private boolean isLevelUpEvent(MainEvent mainEvent) {
        return mainEvent.getClass() == LevelUpEvent.class;
    }

    private boolean isBattleEvent(MainEvent mainEvent) {
        return mainEvent.getClass() == BattleEvent.class;
    }


    public void executeBattle(MainEvent mainEvent) {
        // TODO actually doing battle
        BattleEvent battleEvent = (BattleEvent) mainEvent;
        System.out.println("Wohohohohooooooo");
    }
    private void executeLevelUp(MainEvent mainEvent) {
        LevelUpEvent levelUpEvent = (LevelUpEvent) mainEvent;
        System.out.println("executing the lvl up method");
        Building tempBuilding = buildingServices.findOneBuilding((int) levelUpEvent.getBuildingId());
        tempBuilding.levelUp();
        buildingServices.saveOneBuilding(tempBuilding);
    }

    private void executeTroopUpgrade(MainEvent mainEvent) {
        // TODO building Occupation status?
        UpgradeTroopEvent upgradeTroopEvent = (UpgradeTroopEvent) mainEvent;
        Troop troopToBeUpgraded = troopServices.findOneTroop((int) upgradeTroopEvent.getTroopId());
        troopToBeUpgraded.upgrade();
        troopServices.saveOneTroop(troopToBeUpgraded);
        System.out.println("Upgraded your troop");
    }

    // TODO create controller
    public void cancelEvent(long eventID) {
        mainEventRepo.delete(eventID);
    }
    // TODO battle controller
    public void addNewBattleEvent(long attackerId, ArrayList<Troop> troops, long defenderId){
        BattleEvent battleEvenet = new BattleEvent(System.currentTimeMillis()+15000, attackerId, troops, defenderId);
        mainEventRepo.save(battleEvenet);
    }

    public void addNewUpgradeTroopEvent(long troopId, long barrackId){
        // TODO upgrade troop time calc.; currently 15sec; building occupation status?
        UpgradeTroopEvent upgradingTroop = new UpgradeTroopEvent(System.currentTimeMillis()+15000, barrackId, troopId);
        mainEventRepo.save(upgradingTroop);
    }

    public void addNewLevelUpEvent(long buildingID) {
         Building temporaryBuilding = buildingServices.findOneBuilding(buildingID);
         MainEvent levelUpEvent = new LevelUpEvent(
               (System.currentTimeMillis() + calculateBuildingTime(temporaryBuilding)),  buildingID
                 );
        mainEventRepo.save(levelUpEvent);
    }

    public void addNewCreateTroopEvent(long barrackId) {
        long queueTime = 0;
        List<TrainTroopEvent> allEventForABuilding = trainTroopEventRepo.findAllByBuildingIdOrderByExecutionTimeDesc(barrackId);
        if (allEventForABuilding.size() > 0) {
            MainEvent tempTimedEvent = allEventForABuilding.get(0);
            queueTime += tempTimedEvent.getExecutionTime() - System.currentTimeMillis();
        }
        // TODO add building-occupation-status(?);  handle time formula for troop;
        MainEvent timedEvent = new TrainTroopEvent((System.currentTimeMillis() + queueTime + 60000), barrackId);
        mainEventRepo.save(timedEvent);
    }

    private long calculateBuildingTime(Building building) {
        return 60000* calculateTotalCost(building.getLevel()) / 250;
    }

    private int calculateTotalCost(int buildingLevel) {
        int i = 0;
        int totalCostOfBuilding = 0;
        while (i < buildingLevel) {
            totalCostOfBuilding += i * 100;
            i++;
        }
        return totalCostOfBuilding + 250;
    }

    public List<MainEvent> findAll(){
        return mainEventRepo.findAll();
    }


}
