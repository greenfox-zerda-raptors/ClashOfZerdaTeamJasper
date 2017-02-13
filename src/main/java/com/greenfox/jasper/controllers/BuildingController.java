package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.CustomError;
import com.greenfox.jasper.services.MainEventServices;
import com.greenfox.jasper.dto.BuildingDto;
import com.greenfox.jasper.dto.BuildingResponse;
import com.greenfox.jasper.services.BuildingServices;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.ResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private BuildingServices buildingServices;

    @Autowired
    private MainEventServices mainEventServices;

    @Autowired
    private DTOServices dtoServices;

    @Autowired
    private ResourceServices resourceServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<BuildingResponse> getBuildings(@PathVariable int kingdomId) {
        List<Building> buildingList = buildingServices.findAllBuildingsByKingdomId(kingdomId);

        if (buildingList == null) {
            return new ResponseEntity(new CustomError("Buildings not found", 404), HttpStatus.NOT_FOUND);
        }
        BuildingResponse result = new BuildingResponse(dtoServices.convertBuildingListToDTO(buildingList));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{buildingId}", method = RequestMethod.GET)
    public ResponseEntity<BuildingDto> getOneBuilding(@PathVariable int buildingId) {
        BuildingDto result =
                dtoServices.convertBuildingToDTO(buildingServices.findOneBuilding(buildingId));
        if (result == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/levelup/{buildingId}", method = RequestMethod.PUT)
    public ResponseEntity<BuildingDto> levelUpBuildingById(@PathVariable int kingdomId, @PathVariable int buildingId, HttpServletResponse response) throws IOException {
        boolean available = resourceServices.levelUpBuildingMoneyCheck(kingdomId, buildingId);
        if (!available) {
            return new ResponseEntity(new CustomError("Not enough gold", 400), HttpStatus.BAD_REQUEST);
         }
        mainEventServices.addNewLevelUpEvent((long) buildingId);
        BuildingDto result = dtoServices.convertBuildingToDTO(buildingServices.findOneBuilding(buildingId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/newbuilding/{type}", method = RequestMethod.GET)
    public ResponseEntity<BuildingDto> addNewBuilding(@PathVariable int kingdomId, @PathVariable String type, HttpServletResponse response) throws IOException {
        boolean available = resourceServices.buyNewBuilding(kingdomId);
        if (!available) {
            return new ResponseEntity(new CustomError("Not enough gold", 400), HttpStatus.BAD_REQUEST);
        }
            buildingServices.addNewBuilding(kingdomId, type);
            BuildingDto result = dtoServices.convertBuildingToDTO(buildingServices.findLastBuilding(kingdomId));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
