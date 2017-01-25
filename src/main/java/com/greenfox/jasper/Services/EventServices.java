package com.greenfox.jasper.Services;

import com.greenfox.jasper.Models.GameEvent;
import com.greenfox.jasper.Models.GameItem.Building;
import com.greenfox.jasper.Models.TimedEvent;
import com.greenfox.jasper.Services.Repositories.BuildingRepository;
import com.greenfox.jasper.Services.Repositories.TimedEventRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zoltán on 2017.01.22..
 */
@Service
public class EventServices {


    private TimedEventRepo timedEventRepo; // think about time handling being simplified, 2 second delay
    private BuildingRepository buildingRepository;

    private final Logger log = LoggerFactory.getLogger(EventServices.class);

    @Autowired
    public EventServices(TimedEventRepo timedEventRepo, BuildingRepository buildingRepository){
        this.timedEventRepo = timedEventRepo;
        this.buildingRepository = buildingRepository;
    }

    @Scheduled(fixedRate = 1000) // base principal is solid, execution time needs to be shortened for it to work, test for shorter time
    public void checkForEvents(){
        long currentTime = System.currentTimeMillis();
        List<TimedEvent> listedEvents = timedEventRepo.findAllWaitingForExecution(currentTime);

        for (TimedEvent listedEvent : listedEvents) {
            processEvent(listedEvent);
        }
// needs to be simplified for performance reasons, current amount 1000/sec
    }

    private void processEvent(TimedEvent timedEvent) {
        executeEvent(timedEvent.getBuildingId(), timedEvent.getEvent());
        timedEvent.setWasExecuted(true);
        timedEventRepo.save(timedEvent);
    }

    public void executeEvent(long buildingID, GameEvent events) {
       Building tempBuilding = buildingRepository.findOne(buildingID);
//        Creating troops should be handled in a different repository, still has to figure sth out

        switch (events){
            case LEVELUP:
                tempBuilding.levelUp();
                buildingRepository.save(tempBuilding);
                break;
            case DEMOLISH:
                tempBuilding.demolish();
                buildingRepository.save(tempBuilding);
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
