package com.greenfox.jasper.Services;

import com.greenfox.jasper.Models.*;
import com.greenfox.jasper.Models.GameItem.*;
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
//        Farm ffff = new Farm();
//        Barrack ffvfgbf = new Barrack();
//        Mine ftrrtb = new Mine();
//        buildingRepository.save(ffff);
//        buildingRepository.save(ffvfgbf);
//        buildingRepository.save(ftrrtb);
        long currentTime = System.currentTimeMillis();
        log.info("the time after 60 sec is {}", currentTime+60000);

//        2 sec is added to avoid events sneaking through
        List<TimedEvent> listedEvents = timedEventRepo.findAllByExecutionTimeBetween((currentTime - 7000), currentTime);

        if(listedEvents.size() != 0) {
            for (int i = 0; i < listedEvents.size(); i++) {
                if(!listedEvents.get(i).isWasExecuted()) {
                    processEvent(listedEvents.get(i));
                }
            }
//            if it needs to be deleted
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

    private void executeEvent(long buildingID, GameEvent events) {
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
            case CONSTRUCT:

                break;
            default:
                System.out.println("error");



        }
    }
}
