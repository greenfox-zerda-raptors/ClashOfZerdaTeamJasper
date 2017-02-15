package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.TimedEvent.*;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.repos.TimedEventRepos.TimedEventRepo;
import com.greenfox.jasper.repos.TimedEventRepos.TrainTroopEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimedEventServices {

    private long baseTime = 60000;

    @Autowired
    private TimedEventRepo timedEventRepo;
    @Autowired
    private TrainTroopEventRepo trainTroopEventRepo;
    @Autowired
    private TroopServices troopServices;
    @Autowired
    private ResourceServices resourceServices;
    @Autowired
    private BuildingServices buildingServices;
    @Autowired
    private KingdomServices kingdomServices;

    // @Inheritance, timedEventRepo will obtain everything you need, "subrepos" will only obtain the data for that class (no data from superclass only ID)
    // If you want to filter in different event types, there is a field automatically generated, use custom queries for them

    @Scheduled(fixedRate = 1000)
    public void checkForEvents() {
        long currentTime = System.currentTimeMillis();
        List<TimedEvent> listedEvents = timedEventRepo.findAllWaitingForExecution(currentTime);
        if(listedEvents.size() != 0) {
            for (TimedEvent listedEvent : listedEvents) {
                processEventTest(listedEvent);
            }
        }
    }

    private void processEventTest(TimedEvent timedEvent){

       if(isBattleEvent(timedEvent)){
           executeBattle(timedEvent);
       }else if(isLevelUpEvent(timedEvent)){
           executeLevelUp(timedEvent);
       }else if(isUpgradeTroopEvent(timedEvent)){
           executeTroopUpgrade(timedEvent);
       }else if(isTrainTroopEvent(timedEvent)){
           executeNewTroopEvent(timedEvent);
       } else{
           System.out.println("No such event-method");
       }
        timedEvent.setWasExecuted(true);
        timedEventRepo.save(timedEvent);
    }

    private boolean isTrainTroopEvent(TimedEvent timedEvent) {
        return timedEvent.getClass() == TrainTroopEvent.class;
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
    private void executeNewTroopEvent(TimedEvent timedEvent){
        TrainTroopEvent trainTroopEvent = (TrainTroopEvent) timedEvent;
        Troop troop = new Troop(kingdomServices.findOneById(trainTroopEvent.getKingdomId()));
        troopServices.saveOneTroop(troop);
    }
    private void executeLevelUp(TimedEvent timedEvent) {
        LevelUpEvent levelUpEvent = (LevelUpEvent) timedEvent;
        Building tempBuilding = buildingServices.findOneBuilding(levelUpEvent.getBuildingId());
        tempBuilding.levelUp();
        buildingServices.saveOneBuilding(tempBuilding);
        resourceServices.calculateResource(tempBuilding.getKingdom().getKingdomId());
    }

    private void executeTroopUpgrade(TimedEvent timedEvent) {
        UpgradeTroopEvent upgradeTroopEvent = (UpgradeTroopEvent) timedEvent;
        Troop troopToBeUpgraded = troopServices.findOneTroop(upgradeTroopEvent.getTroopId());
        troopToBeUpgraded.upgrade();
        troopServices.saveOneTroop(troopToBeUpgraded);
    }
    public void cancelEvent(long eventID) {
        TimedEvent tempEvent = timedEventRepo.findOne(eventID);
        tempEvent.setWasExecuted(true);
        // Here retrieve resources etc.
        timedEventRepo.save(tempEvent);
    }
    public void addNewBattleEvent(long attackerId, ArrayList<Troop> troops, long defenderId){
        BattleEvent battleEvent = new BattleEvent(battleTime(), attackerId, troops, defenderId);
        timedEventRepo.save(battleEvent);
    }

    public void addNewUpgradeTroopEvent(long troopId, long kingdomId){
        UpgradeTroopEvent upgradingTroop = new UpgradeTroopEvent(upgradeTroopTime(kingdomId), kingdomId, troopId);
        timedEventRepo.save(upgradingTroop);
    }

    public void addNewLevelUpEvent(long buildingID) {
         Building temporaryBuilding = buildingServices.findOneBuilding(buildingID);
         TimedEvent levelUpEvent = new LevelUpEvent(
                 buildingLevelUpTime(temporaryBuilding),  buildingID
                 );
        timedEventRepo.save(levelUpEvent);
    }

    public void addNewCreateTroopEvent(long kingdomId) {
        TimedEvent timedEvent = new TrainTroopEvent((troopProductionTime(kingdomId)), kingdomId);
        timedEventRepo.save(timedEvent);
    }

    private long getQueueTime(long kingdomId) {
        long queueTime = 0;
        List<TrainTroopEvent> allEventForKingdom = trainTroopEventRepo.findAllByKingdomIdOrderByExecutionTimeDesc(kingdomId);
        if (allEventForKingdom.size() > 0) {
            TimedEvent tempTimedEvent = allEventForKingdom.get(0);
            queueTime += tempTimedEvent.getExecutionTime() - System.currentTimeMillis();
        }
        return queueTime;
    }

    private long calculateBuildingTimeRatio(Building building) {
        int buildingLevel = building.getLevel();
        return calculateTotalCost(buildingLevel) / 250;
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
        return timedEventRepo.findAll();
    }

    public TimedEvent findOne(long id) {
        return timedEventRepo.findOne(id);
    }

    private long upgradeTroopTime(long kingdomId) {
        return System.currentTimeMillis()+baseTime + getQueueTime(kingdomId);
    }
    private long buildingLevelUpTime(Building temporaryBuilding) {
        return System.currentTimeMillis() + baseTime * calculateBuildingTimeRatio(temporaryBuilding);
    }
    public long troopProductionTime(long kingdomId){
        int totalBarrackLevel = buildingServices.calculateTotalLevel(kingdomId, "barrack");
        return System.currentTimeMillis() + baseTime/totalBarrackLevel +  getQueueTime(kingdomId);
    }
    private long battleTime() {
        // TODO scales with kingdom distances
        return System.currentTimeMillis()+baseTime;
    }

}
