package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.GameEvent;
import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.services.EventServices;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/", method = RequestMethod.GET)
public class MainController {

    @Autowired
    MainServices mainServices;

    @Autowired
    EventServices eventServices;

    @RequestMapping(value = "/kingdom/{kingdomName}", method = RequestMethod.GET)
    public User getKingdom(@PathVariable String kingdomName) {
        return mainServices.findKingdom(kingdomName);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable int userId) {
        return mainServices.findOneUser(userId);
    }
    
    @RequestMapping(value = "/building/levelup/{buildingId}", method = RequestMethod.POST)
    public void levelUpBuildingById(@PathVariable int buildingId, HttpServletResponse response) throws IOException {
        eventServices.executeEvent(buildingId, GameEvent.LEVELUP);
        response.sendRedirect(String.format("/building/%d", buildingId));
    }

    @RequestMapping(value = "/building/{buildingId}", method = RequestMethod.GET)
    public Building getBuilding(@PathVariable int buildingId) {
        return mainServices.findOneBuilding(buildingId);
    }

    @RequestMapping(value = "/lvlup/{buildingId}", method = RequestMethod.POST)
    public void addNewEvent(@PathVariable int buildingId, HttpServletResponse response) throws IOException {
        eventServices.addNewLevelUpEvent((long) buildingId);
        response.sendRedirect(String.format("/building/%d", buildingId));
    }

    @RequestMapping(value = "/traintroop/{buildingId}", method = RequestMethod.POST)
    public void addNewCreateTroopEvent(@PathVariable int buildingId, HttpServletResponse response) throws IOException {
        eventServices.addNewCreateTroopEvent((long) buildingId);
        response.sendRedirect(String.format("/building/%d", buildingId));
    }




}
