package com.greenfox.jasper.controllers;

import com.greenfox.jasper.DTO.*;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/kingdom", method = RequestMethod.GET)
public class KingdomController {

    @Autowired
    private MainServices mainServices;

    @RequestMapping(value = "/{kingdomName}", method = RequestMethod.GET)
    public ResponseEntity<Object> getKingdom(@PathVariable String kingdomName) {
        Kingdom kd = mainServices.findKingdomByName(kingdomName);

        if(kd == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("userId not found");
        }

        KingdomDto result = new KingdomDto();
        result.setUser(new UserDto()); // fetch from the user service and map User to UserDto
        result.setBuildings(new ArrayList<BuildingDto>()); // fetch from building service and map Buildings to BuildingDto
        result.setTroops(new ArrayList<TroopDto>()); // fetch from troop service, map
        result.setResources(new ArrayList<ResourceDto>()); // fetch from resource service, map

        return new ResponseEntity<Object>(result, HttpStatus.OK);

    }
}
