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
public class TimedEventServices {

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

        List<TimedEvent> listedEvents = mainEventRepo.findAllWaitingForExecution(currentTime);
        if(listedEvents.size() != 0) {
            for (TimedEvent listedEvent : listedEvents) {
                processEventTest(listedEvent);
            }
        }
//        System.out.println("I finished processing the events in: " + (System.currentTimeMillis() - currentTime));
    }


    private void processEventTest(TimedEvent timedEvent){

       if(isBattleEvent(timedEvent)){
           executeBattle(timedEvent);
       }else if(isLevelUpEvent(timedEvent)){
           executeLevelUp(timedEvent);
       }else if(isUpgradeTroopEvent(timedEvent)){
           executeTroopUpgrade(timedEvent);
       }else{
           System.out.println("No such event-method");
       }
        timedEvent.setWasExecuted(true);
        mainEventRepo.save(timedEvent);
    }

    private boolean isUpgradeTroopEvent(TimedEvent timedEvent) {
        return timedEvent.getClass() == UpgradeTroopEvent.class;
    }

    private boolean isLevelUpEvent(TimedEvent timedEvent) {
        return timedEvent.getClass() == LevelUpEvent.class;
    }

    private boolean isBattleEvent(TimedEvent timedEvent) {
        return timedEvent.getClass() == BattleEvent.class;
    }


    public void executeBattle(TimedEvent timedEvent) {
        // TODO actually doing battle
        BattleEvent battleEvent = (BattleEvent) timedEvent;
    }
    private void executeLevelUp(TimedEvent timedEvent) {
        LevelUpEvent levelUpEvent = (LevelUpEvent) timedEvent;
        Building tempBuilding = buildingServices.findOneBuilding(levelUpEvent.getBuildingId());
        tempBuilding.levelUp();
        buildingServices.saveOneBuilding(tempBuilding);
        resourceServices.calculateResource(tempBuilding.getKingdom().getKingdomId());
    }

    private void executeTroopUpgrade(TimedEvent timedEvent) {
        // TODO building Occupation status?
        UpgradeTroopEvent upgradeTroopEvent = (UpgradeTroopEvent) timedEvent;
        Troop troopToBeUpgraded = troopServices.findOneTroop(upgradeTroopEvent.getTroopId());
        troopToBeUpgraded.upgrade();
        troopServices.saveOneTroop(troopToBeUpgraded);
    }
    public void cancelEvent(long eventID) {
        TimedEvent tempEvent = mainEventRepo.findOne(eventID);
        tempEvent.setWasExecuted(true);
        // Here retrieve resources etc.
        mainEventRepo.save(tempEvent);
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
         TimedEvent levelUpEvent = new LevelUpEvent(
               (System.currentTimeMillis() + calculateBuildingTime(temporaryBuilding)),  buildingID
                 );
        mainEventRepo.save(levelUpEvent);
    }


    public void addNewCreateTroopEvent(long barrackId) {
        long queueTime = 0;
        List<TrainTroopEvent> allEventForABuilding = trainTroopEventRepo.findAllByBuildingIdOrderByExecutionTimeDesc(barrackId);
        if (allEventForABuilding.size() > 0) {
            TimedEvent tempTimedEvent = allEventForABuilding.get(0);
            queueTime += tempTimedEvent.getExecutionTime() - System.currentTimeMillis();
        }
        // TODO add building-occupation-status(?);  handle time formula for troop;
        TimedEvent timedEvent = new TrainTroopEvent((System.currentTimeMillis() + queueTime + 60000), barrackId);
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

    public List<TimedEvent> findAll(){
        return mainEventRepo.findAll();
    }

    public void addTestLevelUpEvent(long buildingId, long time){
        TimedEvent levelUpEvent = new LevelUpEvent(time, buildingId);
        mainEventRepo.save(levelUpEvent);
    }
    public void addTestUpgradeTroopEvent(long troopId, long barrackId, long time){
        TimedEvent upgrade = new UpgradeTroopEvent(time, barrackId, troopId);
        mainEventRepo.save(upgrade);
    }

    public TimedEvent findOne(long id) {
        return mainEventRepo.findOne(id);
    }
}
