package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.dto.BuildingDto;
import com.greenfox.jasper.dto.BuildingResponse;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.EventServices;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/kingdom/{kingdomId}/buildings")
public class BuildingController {

    @Autowired
    private MainServices mainServices;

    @Autowired
    private EventServices eventServices;

    @Autowired
    private DTOServices dtoServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public BuildingResponse getBuildingsList(@PathVariable int kingdomId, HttpServletResponse response) throws IOException {
        List<Building> buildingList = mainServices.findAllBuildingsByKingdomId(kingdomId);

        if(buildingList == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND, "No buildings available");
        }

        BuildingResponse result = new BuildingResponse(dtoServices.convertBuildingListToDTO(buildingList));

        return result;
    }

    @RequestMapping(value = "/{buildingId}", method = RequestMethod.GET)
    public BuildingDto getOneBuilding(@PathVariable int buildingId, HttpServletResponse response) throws IOException {
        BuildingDto result =
                dtoServices.convertBuildingToDTO(mainServices.findOneBuilding(buildingId));
        if(result == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND, "No such building");
        }
        return result;
    }


    // TODO this should (probably) not redirect
    @RequestMapping(value = "/levelup/{buildingId}", method = RequestMethod.GET)
    public void levelUpBuildingById(@PathVariable int buildingId, HttpServletResponse response) throws IOException {
        eventServices.addNewLevelUpEvent((long) buildingId);
        response.sendRedirect("/kingdom/2/buildings");
    }

    @RequestMapping(value = "/newbuilding/{type}", method = RequestMethod.GET)
    public void addNewBuilding(@PathVariable int kingdomId , @PathVariable String type, HttpServletResponse response) throws IOException{
        mainServices.addNewBuilding(kingdomId, type);
        response.sendRedirect("/kingdom/2/buildings");
    }
}

