package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.responses.BuildingResponse;
import com.greenfox.jasper.services.EventServices;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;

@RestController
@RequestMapping(value = "/kingdom/{userId}/buildings")
public class BuildingController {

    @Autowired
    private MainServices mainServices;

    @Autowired
    private EventServices eventServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public BuildingResponse getBuildings(@PathVariable int userId) {
        BuildingResponse response = new BuildingResponse();
        for(Building building : mainServices.findAllBuildingsByKingdomId(mainServices.findOneUser(userId).getKingdom().getKingdomId())) {
            response.getBuildings().add(building);
        }
        return response;
    }

    @RequestMapping(value = "/{buildingId}", method = RequestMethod.GET)
    public Building getOneBuilding(@PathVariable int buildingId) {
        return mainServices.findOneBuilding(buildingId);
    }

    @RequestMapping(value = "/levelup/{buildingId}", method = RequestMethod.GET)
    public void levelUpBuildingById(@PathVariable int buildingId, HttpServletResponse response) throws IOException {
        eventServices.addNewLevelUpEvent((long) buildingId);
        response.sendRedirect("/kingdom/1/buildings");
    }
    @RequestMapping(value = "/newbuilding/{type}", method = RequestMethod.GET)
    public void addNewBuilding(@PathVariable int userId , @PathVariable String type, HttpServletResponse response) throws IOException{
        mainServices.addNewBuilding(userId, type);
        response.sendRedirect("/kingdom/1/buildings");
    }
}

