package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.GameEvent;
import com.greenfox.jasper.domain.TimedEvent;
import com.greenfox.jasper.repos.BuildingRepo;
import com.greenfox.jasper.repos.TimedEventRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServices {


    private TimedEventRepo timedEventRepo;
    private BuildingRepo buildingRepo;

    private final Logger log = LoggerFactory.getLogger(EventServices.class);

    @Autowired
    public EventServices(TimedEventRepo timedEventRepo, BuildingRepo buildingRepo){
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

        //TODO Creating troops should be handled in a different repository (eg.: TroopRepo @Autowired)

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
                log.info("Deleveled building with id {} to level {}", tempBuilding.getBuildingId(), tempBuilding.getLevel());
                break;
            case UPGRADETROOPS:
                System.out.println("Troops being upgraded");
                break;
            case TRAINTROOPS:
                System.out.println("Troop has been trained");
                break;
            default:
                System.out.println("error");
        }
    }

    public void addNewLevelUpEvent(long buildingID){
//        Building temporaryBuilding = buildingRepo.findOne(buildingID);
        // TODO formula for building level up times
        TimedEvent timedEvent = new TimedEvent(buildingID, (System.currentTimeMillis() + 30000), GameEvent.LEVELUP );
        timedEventRepo.save(timedEvent);
    }

    public TimedEvent findOne(long id){
        return timedEventRepo.findOne(id);
    }
}
