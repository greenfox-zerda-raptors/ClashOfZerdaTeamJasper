package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.TimedEvent.BattleEvent;
import com.greenfox.jasper.domain.TimedEvent.LevelUpEvent;
import com.greenfox.jasper.domain.TimedEvent.TimedEvent;
import com.greenfox.jasper.domain.TimedEvent.UpgradeTroopEvent;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.repos.TimedEventRepos.BattleEventRepo;
import com.greenfox.jasper.repos.TimedEventRepos.LevelUpEventRepo;
import com.greenfox.jasper.repos.TimedEventRepos.TimedEventRepo;
import com.greenfox.jasper.repos.TimedEventRepos.UpgradeTroopEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimedEventServices {

    private long baseTime = 5000; // currently set for 1 min., more balance changes required

    @Autowired
    private TimedEventRepo timedEventRepo;
    @Autowired
    private UpgradeTroopEventRepo upgradeTroopEventRepo;
    @Autowired
    private LevelUpEventRepo levelUpEventRepo;
    @Autowired
    private BattleEventRepo battleEventRepo;
    @Autowired
    private TroopServices troopServices;
    @Autowired
    private ResourceServices resourceServices;
    @Autowired
    private BuildingServices buildingServices;
    @Autowired
    private KingdomServices kingdomServices;


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
       } else{
           System.out.println("No such event-method");
       }
        timedEvent.setWasExecuted(1);
        timedEventRepo.save(timedEvent);
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
        System.out.println("battle donezo");
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
        tempEvent.setWasExecuted(1);
        timedEventRepo.save(tempEvent);
    }
    public void addNewBattleEvent(long attackerId, List<Troop> troops, long defenderId){
        BattleEvent battleEvent = new BattleEvent(battleTime(attackerId, defenderId), attackerId, troops, defenderId);
        timedEventRepo.save(battleEvent);
    }

    public void addNewUpgradeTroopEvent(long troopId, long kingdomId){
        long upgradeTroopTime = upgradeTroopTime(kingdomId);
        UpgradeTroopEvent upgradingTroop = new UpgradeTroopEvent(upgradeTroopTime,kingdomId, troopId);
        timedEventRepo.save(upgradingTroop);
        Troop troop = troopServices.findOneTroop(troopId);
        troop.setUpgradeTime(upgradeTroopTime);
        troopServices.saveOneTroop(troop);
    }

    public void addNewLevelUpEvent(long kingdomId, Building building) {
        long buildingLevelUpTime = buildingLevelUpTime(building, kingdomId);
        building.setLevelUpTime(buildingLevelUpTime);
         TimedEvent levelUpEvent = new LevelUpEvent(
                 buildingLevelUpTime, kingdomId, building.getBuildingId());
        timedEventRepo.save(levelUpEvent);
        buildingServices.saveOneBuilding(building);
    }

    private long getQueueTimeForTroopEvents(long kingdomId) {
        long queueTime = 0;
        List<UpgradeTroopEvent> allTroopEventForKingdom = upgradeTroopEventRepo.findAllByKingdomIdAndWasExecutedOrderByExecutionTimeDesc(kingdomId, 0);
        if (allTroopEventForKingdom.size() > 0) {
            TimedEvent tempTimedEvent = allTroopEventForKingdom.get(0);
            queueTime += tempTimedEvent.getExecutionTime() - System.currentTimeMillis();
        }
        return queueTime;
    }
    private long getQueueTimeForBuildings(long kingdomId) {
        long queueTime = 0;
        List<LevelUpEvent> allBuildingEventForKingdom = levelUpEventRepo.findAllByKingdomIdAndWasExecutedOrderByExecutionTimeDesc(kingdomId, 0);
        if (allBuildingEventForKingdom.size() > 0) {
            TimedEvent tempTimedEvent = allBuildingEventForKingdom.get(0);
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
        return System.currentTimeMillis() + baseTime + getQueueTimeForTroopEvents(kingdomId);
    }
    private long buildingLevelUpTime(Building temporaryBuilding, long kingdomId) {
        return System.currentTimeMillis() + baseTime * calculateBuildingTimeRatio(temporaryBuilding) + getQueueTimeForBuildings(kingdomId);
    }
    private long battleTime(long attackerId, long defenderId) {
        // TODO scales with kingdom distances
        Kingdom one = kingdomServices.findOneById(attackerId);
        Kingdom two = kingdomServices.findOneById(defenderId);
        return System.currentTimeMillis() + baseTime;
    }

    private double calcuateDistance(int posX, int posY, int posX1, int posY1) {
        return Math.sqrt((Math.pow(posX - posX1, 2)) + Math.pow(posY - posY1, 2));
    }



}
