package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.GameEvent;
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
@RequestMapping(value = "/kingdom/{userId}/buildings", method = RequestMethod.GET)
public class BuildingController {

    @Autowired
    private MainServices mainServices;

    @Autowired
    private EventServices eventServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Building> getBuildings(@PathVariable int userId) {
        return mainServices.findAllBuildingsByKingdomId(mainServices.findOneUser(userId).getKingdom().getKingdomId());
    }

    @RequestMapping(value = "/{buildingId}", method = RequestMethod.GET)
    public Building getOneBuilding(@PathVariable int buildingId) {
        return mainServices.findOneBuilding(buildingId);
    }


    @RequestMapping(value = "/levelup/{buildingId}", method = RequestMethod.POST)
    public void levelUpBuildingById(@PathVariable int buildingId, HttpServletResponse response) throws IOException {
        eventServices.executeEvent(buildingId, GameEvent.LEVELUP);
        response.sendRedirect(String.format("/building/%d", buildingId));
    }
}
