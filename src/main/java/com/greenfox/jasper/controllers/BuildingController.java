package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.CustomError;
import com.greenfox.jasper.dto.BuildingDto;
import com.greenfox.jasper.dto.BuildingResponse;
import com.greenfox.jasper.services.BuildingServices;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.ResourceServices;
import com.greenfox.jasper.services.TimedEventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/kingdom/{kingdomId}/buildings")
public class BuildingController {

    @Autowired
    private BuildingServices buildingServices;

    @Autowired
    private TimedEventServices timedEventServices;

    @Autowired
    private DTOServices dtoServices;

    @Autowired
    private ResourceServices resourceServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<BuildingResponse> getBuildings(@PathVariable long kingdomId) {
        List<Building> buildingList = buildingServices.findAllBuildingsByKingdomId(kingdomId);

        if (buildingList == null) {
            return new ResponseEntity(new CustomError("Buildings not found", 404), HttpStatus.NOT_FOUND);
        }
        BuildingResponse result = new BuildingResponse(dtoServices.convertBuildingListToDTO(buildingList));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{buildingId}", method = RequestMethod.GET)
    public ResponseEntity<BuildingDto> getOneBuilding(@PathVariable long buildingId) {
        BuildingDto result =
                dtoServices.convertBuildingToDTO(buildingServices.findOneBuilding(buildingId));
        if (result == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/newbuilding", method = RequestMethod.POST)
    public ResponseEntity addNewBuildingPost(@PathVariable long kingdomId, @RequestBody Building building) {
        boolean available = resourceServices.buyNewBuilding(kingdomId);
        if (!available) {
            return new ResponseEntity(new CustomError("Not enough gold", 400), HttpStatus.BAD_REQUEST);
        }
        buildingServices.addNewBuilding(kingdomId, building.getType());
        BuildingDto result = dtoServices.convertBuildingToDTO(buildingServices.findLastBuilding(kingdomId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/upgrade", method = RequestMethod.POST)
    public ResponseEntity upgradeBuilding(@PathVariable long kingdomId, @RequestBody Building building) {
        long buildingId = building.getBuildingId();
        boolean available = resourceServices.levelUpBuildingMoneyCheck(kingdomId, buildingId);
        if (!available) {
            return new ResponseEntity(new CustomError("Not enough gold", 400), HttpStatus.BAD_REQUEST);
        }
        timedEventServices.addNewLevelUpEvent(buildingId);
        BuildingDto result = dtoServices.convertBuildingToDTO(buildingServices.findOneBuilding(buildingId));
        return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

//    @RequestMapping(value = "/levelup/{buildingId}", method = RequestMethod.PUT)
//    public ResponseEntity<BuildingDto> levelUpBuildingById(@PathVariable int kingdomId, @PathVariable int buildingId) throws IOException {
//        boolean available = resourceServices.levelUpBuildingMoneyCheck(kingdomId, buildingId);
//        if (!available) {
//            return new ResponseEntity(new CustomError("Not enough gold", 400), HttpStatus.BAD_REQUEST);
//         }
//        timedEventServices.addNewLevelUpEvent(buildingId);
//        BuildingDto result = dtoServices.convertBuildingToDTO(buildingServices.findOneBuilding(buildingId));
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//
//    @RequestMapping(value = "/newbuilding/{type}", method = RequestMethod.GET)
//    public ResponseEntity<BuildingDto> addNewBuilding(@PathVariable int kingdomId, @PathVariable String type) throws IOException {
//        boolean available = resourceServices.buyNewBuilding(kingdomId);
//        if (!available) {
//            return new ResponseEntity(new CustomError("Not enough gold", 400), HttpStatus.BAD_REQUEST);
//        }
//            buildingServices.addNewBuilding(kingdomId, type);
//            BuildingDto result = dtoServices.convertBuildingToDTO(buildingServices.findLastBuilding(kingdomId));
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }
//    }
