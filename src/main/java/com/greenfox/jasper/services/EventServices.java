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


    private TimedEventRepo timedEventRepo; // think about time handling being simplified, 2 second delay
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
        //TODO Creating troops should be handled in a different repository, still has to figure sth out

        switch (events){
            case LEVELUP:
                tempBuilding.levelUp();
                buildingRepo.save(tempBuilding);
                break;
            case DEMOLISH:
                tempBuilding.decreaseLvl();
                buildingRepo.save(tempBuilding);
                System.out.println("Demolished building");
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
        TimedEvent timedEvent = new TimedEvent(buildingID, (System.currentTimeMillis() + 30000), GameEvent.LEVELUP );
        timedEventRepo.save(timedEvent);
    }

    public TimedEvent findOne(long id){
        return timedEventRepo.findOne(id);
    }
}
