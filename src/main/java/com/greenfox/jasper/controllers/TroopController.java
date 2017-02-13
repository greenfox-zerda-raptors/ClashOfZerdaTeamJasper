package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.CustomError;
import com.greenfox.jasper.services.TimedEventServices;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.dto.TroopResponse;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.TroopServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/kingdom/{kingdomId}/troops", method = RequestMethod.GET)
public class TroopController {

    @Autowired
    private TroopServices troopServices;

    @Autowired
    private TimedEventServices timedEventServices;

    @Autowired
    private DTOServices DTOServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<TroopResponse> getTroops(@PathVariable long kingdomId, HttpServletResponse response) {
        List<Troop> troopList = troopServices.findAllTroopsByKingdomId(kingdomId);
        if(troopList == null){
            return new ResponseEntity(new CustomError("No troops found", 404), HttpStatus.NOT_FOUND);
        }
        TroopResponse result = new TroopResponse(DTOServices.convertTroopListToDTO(troopList));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{troopId}", method = RequestMethod.GET)
    public Troop getOneTroop(@PathVariable long troopId) {
        return troopServices.findOneTroop(troopId);
    }

    @RequestMapping(value = "/new/{barrackId}", method = RequestMethod.GET)
    public void trainNewTroop(@PathVariable long barrackId) {
        timedEventServices.addNewCreateTroopEvent(barrackId);
    }

    @RequestMapping(value = "/{troopId}/upgrade/{barrackId}", method = RequestMethod.GET)
    public void upgradeTroop(@PathVariable int troopId, @PathVariable long barrackId){
        timedEventServices.addNewUpgradeTroopEvent(troopId, barrackId);
    }

}
