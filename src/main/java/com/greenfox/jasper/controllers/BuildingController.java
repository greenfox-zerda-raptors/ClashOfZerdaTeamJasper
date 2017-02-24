package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.dto.BuildingDto;
import com.greenfox.jasper.dto.BuildingListDTO;
import com.greenfox.jasper.exception.badrequest.BuildingOccupiedException;
import com.greenfox.jasper.exception.badrequest.NotEnoughResourcesException;
import com.greenfox.jasper.exception.notfound.BuildingNotFoundException;
import com.greenfox.jasper.exception.notfound.KingdomNotFoundException;
import com.greenfox.jasper.exception.notfound.NoBuildingsFoundException;
import com.greenfox.jasper.security.JwtUser;
import com.greenfox.jasper.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/kingdom/buildings")
public class BuildingController {

    @Autowired
    private KingdomServices kingdomServices;

    @Autowired
    private BuildingServices buildingServices;

    @Autowired
    private TimedEventServices timedEventServices;

    @Autowired
    private DTOServices dtoServices;

    @Autowired
    private ResourceServices resourceServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<BuildingListDTO> getBuildings(@AuthenticationPrincipal JwtUser currentUser) {
        long kingdomId = kingdomServices.getKingdomIdFromJWTUser(currentUser);
        List<Building> buildingList = buildingServices.findAllBuildingsByKingdomId(kingdomId);
        if (buildingList == null) {
            throw new NoBuildingsFoundException(kingdomId);
        }
        BuildingListDTO result = new BuildingListDTO(dtoServices.convertBuildingListToDTO(buildingList));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{buildingId}", method = RequestMethod.GET)
    public ResponseEntity<BuildingDto> getOneBuilding(@PathVariable long buildingId) {
        Building building = buildingServices.findOneBuilding(buildingId);
        if (building == null) {
            throw new BuildingNotFoundException(buildingId);
        }
        BuildingDto result = dtoServices.convertBuildingToDTO(building);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<BuildingDto> addNewBuilding(@AuthenticationPrincipal JwtUser currentUser,@RequestBody Building building) {
        long kingdomId = kingdomServices.getKingdomIdFromJWTUser(currentUser);
        boolean available = resourceServices.buyNewBuilding(kingdomId);
        if (!available) {
            throw new NotEnoughResourcesException("gold");
        }
        buildingServices.addNewBuilding(kingdomId, building.getType());
        BuildingDto result = dtoServices.convertBuildingToDTO(buildingServices.findLastBuilding(kingdomId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/upgrade", method = RequestMethod.POST)
    public ResponseEntity<BuildingDto> upgradeBuilding(@AuthenticationPrincipal JwtUser currentUser, @RequestBody BuildingDto buildingDto) {
        long kingdomId = kingdomServices.getKingdomIdFromJWTUser(currentUser);
        if (kingdomId == 0) {
             throw new KingdomNotFoundException(kingdomId);
        }
        long buildingId = buildingDto.getId();
        Building buildingToBeUpgraded = buildingServices.findOneBuilding(buildingId);
        if(buildingToBeUpgraded == null){
             throw new BuildingNotFoundException(buildingId);
        }
        if(buildingToBeUpgraded.getLevelUpTime() !=0){
           throw new BuildingOccupiedException(buildingId);
        }
        boolean available = resourceServices.levelUpBuildingMoneyCheck(kingdomId, buildingId);
        if (!available) {
            throw new NotEnoughResourcesException("gold");
        }

        timedEventServices.addNewLevelUpEvent(kingdomId, buildingToBeUpgraded);
        BuildingDto result = dtoServices.convertBuildingToDTO(buildingToBeUpgraded);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

