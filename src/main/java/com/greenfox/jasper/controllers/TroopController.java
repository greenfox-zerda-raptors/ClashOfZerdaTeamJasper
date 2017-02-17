package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.CustomError;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.dto.TroopDto;
import com.greenfox.jasper.dto.TroopPostDto;
import com.greenfox.jasper.dto.troopListDTO;
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
    private DTOServices dtoServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<troopListDTO> getTroops(@AuthenticationPrincipal JwtUser currentUser) {
        long kingdomId = kingdomServices.getKingdomIdFromJWTUser(currentUser);
        List<Troop> troopList = troopServices.findAllTroopsByKingdomId(kingdomId);
        if (troopList == null) {
            return new ResponseEntity(new CustomError("No troops found", 404), HttpStatus.NOT_FOUND);
        }
        troopListDTO result = new troopListDTO(dtoServices.convertTroopListToDTO(troopList));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{troopId}", method = RequestMethod.GET)
    public ResponseEntity<TroopDto> getOneTroop(@PathVariable long troopId) {
        Troop result = troopServices.findOneTroop(troopId);
        if (result == null) {
            return new ResponseEntity(new CustomError("No troop found with that id", 404), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(dtoServices.convertTRoopToDTO(result));
    }


    // TODO remove building time cd to kingdom - calculate max available troop (from barrack level)
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity trainNewTroop(@AuthenticationPrincipal JwtUser currentUser) {
        long kingdomId = kingdomServices.getKingdomIdFromJWTUser(currentUser);
        troopServices.addNewTroop(kingdomId);
        return ResponseEntity.status(HttpStatus.OK).body("Mkay");
    }

    // TODO remove building dependency cd to kingdom
    @RequestMapping(value = "/upgrade", method = RequestMethod.POST)
    public ResponseEntity upgradeTroop(@AuthenticationPrincipal JwtUser currentUser, @RequestBody TroopPostDto troopPostDto) {
        long kingdomId = kingdomServices.getKingdomIdFromJWTUser(currentUser);
        if (kingdomId == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such kingdom");
        }
        timedEventServices.addNewUpgradeTroopEvent(troopPostDto.getTroopId(), kingdomId);
        return ResponseEntity.status(HttpStatus.OK).body("Troop with id " + troopPostDto.getTroopId() + " will be upgraded");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<TroopDto> delete(@AuthenticationPrincipal JwtUser currentUser, @RequestBody TroopPostDto troopPostDto){
        Troop result = troopServices.findOneTroop(troopPostDto.getTroopId());
        troopServices.deleteTest(troopPostDto.getTroopId());
        return ResponseEntity.status(HttpStatus.OK).body(dtoServices.convertTRoopToDTO(result));
    }


}
