package com.greenfox.jasper.domain.TimedEvent;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.GameEvent;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.repos.MainEventRepo;
import com.greenfox.jasper.services.BuildingServices;
import com.greenfox.jasper.services.KingdomServices;
import com.greenfox.jasper.services.ResourceServices;
import com.greenfox.jasper.services.TroopServices;
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
    BuildingServices buildingServices;

    @Autowired
    TroopServices troopServices;

    @Autowired
    KingdomServices kingdomServices;

    @Autowired
    ResourceServices resourceServices;


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
    public void cancelEvent(long eventID) {
        // TODO resolve
        timedEventRepo.delete(eventID);
    }


    // TODO     duplicated       R E S O L V E
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

    public void addNewLevelUpEvent(long buildingID) {

        Building temporaryBuilding = buildingServices.findOneBuilding((int) buildingID);

        // TODO resolve
         timedEvent = new TimedEvent(
                buildingID, (System.currentTimeMillis()
                + (60000
                * calculateBuildingTime(temporaryBuilding.getLevel()))),
                GameEvent.LEVELUP);

        mainEventRepo.save(timedEvent);
    }

    public void addNewCreateTroopEvent(long barrackId) {
        // TODO resolve
        long queueTime = 0;
        List<TimedEvent> allEventForABuilding = timedEventRepo.findAllByBuildingIdOrderByExecutionTimeDesc(barrackId);
        if (allEventForABuilding.size() > 0) {
            TimedEvent tempTimedEvent = allEventForABuilding.get(0);
            queueTime += tempTimedEvent.getExecutionTime() - System.currentTimeMillis();
        }
        // TODO add building-occupation-status(?);  handle time formula for troop;
        TimedEvent timedEvent = new TimedEvent(barrackId, (System.currentTimeMillis() + queueTime + 60000), GameEvent.TRAINTROOPS);
        timedEventRepo.save(timedEvent);
    }

    private long calculateBuildingTime(int buildingLevel) {
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


}
