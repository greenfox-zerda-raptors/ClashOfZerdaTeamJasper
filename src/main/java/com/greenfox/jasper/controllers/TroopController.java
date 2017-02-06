package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.dto.TroopResponse;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.EventServices;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/kingdom/{kingdomId}/troops", method = RequestMethod.GET)
public class TroopController {

    @Autowired
    private MainServices mainServices;

    @Autowired
    private EventServices eventServices;

    @Autowired
    private DTOServices dtoServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<TroopResponse> getTroops(@PathVariable int kingdomId, HttpServletResponse response) {
        List<Troop> troopList = mainServices.findAllTroopsByKingdomId(kingdomId);

        if(troopList==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND, "No such troop");
        }

        TroopResponse result = new TroopResponse(dtoServices.convertTroopListToDTO(troopList));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{troopId}", method = RequestMethod.GET)
    public Troop getOneTroop(@PathVariable int troopId) {
        return mainServices.findOneTroop(troopId);
    }

    @RequestMapping(value = "/new/{barrackId}", method = RequestMethod.GET)
    public void trainNewTroop(@PathVariable int barrackId) {
        eventServices.addNewCreateTroopEvent((long) barrackId);
    }

}
