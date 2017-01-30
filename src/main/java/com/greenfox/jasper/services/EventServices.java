package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.*;
import com.greenfox.jasper.repos.BuildingRepo;
import com.greenfox.jasper.repos.TimedEventRepo;
import com.greenfox.jasper.repos.TroopRepo;
import com.greenfox.jasper.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServices {


    private UserRepo userRepo;
    private TimedEventRepo timedEventRepo;
    private BuildingRepo buildingRepo;
    private TroopRepo troopRepo;

    private final Logger log = LoggerFactory.getLogger(EventServices.class);

    @Autowired
    public EventServices(UserRepo userRepo, TimedEventRepo timedEventRepo, BuildingRepo buildingRepo, TroopRepo troopRepo){
        this.userRepo = userRepo;
        this.troopRepo = troopRepo;
        this.timedEventRepo = timedEventRepo;
        this.buildingRepo = buildingRepo;

    }

    @Scheduled(fixedRate = 1000)
    public void checkForEvents(){
        long currentTime = System.currentTimeMillis();
        List<TimedEvent> listedEvents = timedEventRepo.findAllWaitingForExecution(currentTime);

        for (TimedEvent listedEvent : listedEvents) {
            processEvent(listedEvent);
        }
    }

    private void processEvent(TimedEvent timedEvent) {
        executeEvent(timedEvent.getBuildingId(), timedEvent.getEvent());
        timedEvent.setWasExecuted(true);
        timedEventRepo.save(timedEvent);
    }

    public void executeEvent(long buildingID, GameEvent events) {
        Building tempBuilding = buildingRepo.findOne(buildingID);
        User tempUser = tempBuilding.getUser();

        //TODO Battle event

//        Added log.info to display what is happening;            to be removed

        switch (events){
            case LEVELUP:
                tempBuilding.levelUp();
                buildingRepo.save(tempBuilding);
                log.info("Leveled up building with id {} to level {}", tempBuilding.getBuildingId(), tempBuilding.getLevel());
                break;
            case DELEVEL:
                tempBuilding.decreaseLvl();
                buildingRepo.save(tempBuilding);
                log.info("De-leveled building with id {} to level {}", tempBuilding.getBuildingId(), tempBuilding.getLevel());
                break;
            case UPGRADETROOPS:
                // TODO level up the selected troop - problems will occur
                System.out.println("Troops being upgraded");
                break;
            case TRAINTROOPS:
                // TODO add create a new troop for the kingdom/user
                Troop tempTroop = new Troop(tempUser);
                troopRepo.save(tempTroop);
                log.info("Troop with id {} and userid {} has been trained by building with ID {}", tempTroop.getTroopId(), tempUser.getUserId(), tempBuilding.getBuildingId());
                break;
            default:
                System.out.println("Error, no such event found");
        }
    }

    public void cancelEvent(long eventID){
        timedEventRepo.delete(eventID);
    }

    public void addNewLevelUpEvent(long buildingID){
//        Building temporaryBuilding = buildingRepo.findOne(buildingID);
        // TODO formula for building level up times
        TimedEvent timedEvent = new TimedEvent(buildingID, (System.currentTimeMillis() + 30000), GameEvent.LEVELUP );
        timedEventRepo.save(timedEvent);
    }

    public void addNewCreateTroopEvent(long buildingId){
        long queueTime = 0;
       List<TimedEvent> allEventForABuilding =  timedEventRepo.findAllByBuildingIdOrderByExecutionTime(buildingId);
        if(allEventForABuilding.size() > 0){
           TimedEvent tempTimedEvent = allEventForABuilding.get(allEventForABuilding.size() - 1);
           queueTime += tempTimedEvent.getExecutionTime() - System.currentTimeMillis();
        }
        // TODO add building-occupation-status;  handle time formula for building level;
        TimedEvent timedEvent = new TimedEvent(buildingId, (System.currentTimeMillis() + queueTime + 30000), GameEvent.TRAINTROOPS);
        timedEventRepo.save(timedEvent);
    }

}
