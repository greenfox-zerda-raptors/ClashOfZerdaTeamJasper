package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.*;
import com.greenfox.jasper.domain.TimedEvent.MainEvent;
import com.greenfox.jasper.repos.BuildingRepo;
import com.greenfox.jasper.repos.MainEventRepo;
import com.greenfox.jasper.repos.TroopRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainEventServices {


    @Autowired
    private MainEventRepo mainEventRepo;
    @Autowired
    private BuildingRepo buildingRepo;
    @Autowired
    private TroopRepo troopRepo;
    @Autowired
    private ResourceServices resourceServices;
    @Autowired
    private BuildingServices buildingServices;

    private final Logger log = LoggerFactory.getLogger(MainEventServices.class);


    @Scheduled(fixedRate = 1000)
    public void checkForEvents() {
        long currentTime = System.currentTimeMillis();
        List<MainEvent> listedEvents = mainEventRepo.findAllWaitingForExecution(currentTime);

        for (MainEvent listedEvent : listedEvents) {
            processEvent(listedEvent);
        }
    }

    private void processEvent(MainEvent timedEvent) {
    }



    public void cancelEvent(long eventID) {
        mainEventRepo.delete(eventID);
    }

    public void addNewLevelUpEvent(long buildingID) {
    }

    public void addNewCreateTroopEvent(long buildingId) {
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
