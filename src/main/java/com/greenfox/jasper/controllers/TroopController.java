package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.CustomError;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.dto.BuildingDto;
import com.greenfox.jasper.dto.TroopPostDto;
import com.greenfox.jasper.dto.TroopResponse;
import com.greenfox.jasper.security.JwtUser;
import com.greenfox.jasper.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/kingdom/troops", method = RequestMethod.GET)
public class TroopController {

    @Autowired
    private KingdomServices kingdomServices;

    @Autowired
    private TroopServices troopServices;

    @Autowired
    private TimedEventServices timedEventServices;

    @Autowired
    private BuildingServices buildingServices;

    @Autowired
    private DTOServices DTOServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<TroopResponse> getTroops(@AuthenticationPrincipal JwtUser currentUser) {
        long kingdomId = kingdomServices.getKingdomIdFromJWTUser(currentUser);
        List<Troop> troopList = troopServices.findAllTroopsByKingdomId(kingdomId);
        if (troopList == null) {
            return new ResponseEntity(new CustomError("No troops found", 404), HttpStatus.NOT_FOUND);
        }
        TroopResponse result = new TroopResponse(DTOServices.convertTroopListToDTO(troopList));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{troopId}", method = RequestMethod.GET)
    public ResponseEntity getOneTroop(@PathVariable long troopId) {
        Troop result =  troopServices.findOneTroop(troopId);
        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such troop");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/new/{barrackId}", method = RequestMethod.POST)
    public ResponseEntity trainNewTroop(@RequestBody BuildingDto buildingDto) {
        Building barrack = buildingServices.findOneBuilding(buildingDto.getId());
        if( barrack == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such building");
        }else if(!barrack.getType().contains("barrack")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not a barrack");
        }
        timedEventServices.addNewCreateTroopEvent(buildingDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Mkay");
    }

    @RequestMapping(value = "/upgrade/", method = RequestMethod.POST)
    public ResponseEntity upgradeTroop(@RequestBody TroopPostDto troopPostDto){
        Building barrack = buildingServices.findOneBuilding(troopPostDto.getBarrackId());
        if( barrack == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such building");
        }else if(!barrack.getType().contains("barrack")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not a barrack");
        }
        timedEventServices.addNewUpgradeTroopEvent(troopPostDto.getTroopId(), troopPostDto.getBarrackId());
        return ResponseEntity.status(HttpStatus.OK).body("Troop with id " + troopPostDto.getTroopId() + " will be upgraded");
    }

}
