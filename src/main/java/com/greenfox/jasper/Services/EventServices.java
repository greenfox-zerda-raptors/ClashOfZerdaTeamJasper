package com.greenfox.jasper.Services;

import com.greenfox.jasper.Models.AvailableEvent;
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

    private TimedEventRepo timedEventRepo;
    private BuildingRepository buildingRepository;

    private  final Logger log = LoggerFactory.getLogger(EventServices.class);



    @Autowired
    public EventServices(TimedEventRepo timedEventRepo, BuildingRepository buildingRepository){
        this.timedEventRepo = timedEventRepo;
        this.buildingRepository = buildingRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void checkForEvents(){
        long currentTime = System.currentTimeMillis();
        log.info("the time after 60 sec is {}", currentTime+60000);

//        List<TimedEvent> listedEvents =  timedEventRepo.findAllByExecutionTimeLessThanEqual(currentTime);
        List<TimedEvent> listedEvents = timedEventRepo.findAllByExecutionTimeBetween((currentTime - 7000), currentTime);

        if(listedEvents.size() != 0) {
            for (int i = 0; i < listedEvents.size(); i++) {
                if(listedEvents.get(i).isWasExecuted() == false) {
                    processEvent(listedEvents.get(i));
                }
            }
//            if it need deletation
//            for(int k = listedEvents.size() - 1; k >= 0; k--){
//                timedEventRepo.delete(listedEvents.get(k));
//            }
        }

    }

    private void processEvent(TimedEvent timedEvent) {
        executeEvent(timedEvent.getBuildingId(), timedEvent.getEvent());
        timedEvent.setWasExecuted(true);
        timedEventRepo.save(timedEvent);
    }

    private void executeEvent(long buildingID, AvailableEvent events) {
       Building tempBuilding = buildingRepository.findOne(buildingID);

//        Creating troops should be handled in a different repository, still has to figure sth out
//        Here is where Csaba's statemachine could come handy

        System.out.println("Event read");

        switch (events){
            case LEVELUP:
                System.out.println("Reqistered lvlup command");
                tempBuilding.levelUp();
                buildingRepository.save(tempBuilding);
                System.out.println("Building is leveled up");
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
}
