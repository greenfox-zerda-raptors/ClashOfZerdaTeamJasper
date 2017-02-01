package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.services.EventServices;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kingdom/{userId}/troops", method = RequestMethod.GET)
public class TroopController {

    @Autowired
    private MainServices mainServices;

    @Autowired
    private EventServices eventServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Troop> getTroops(@PathVariable int userId) {
        return mainServices.findAllTroopsByKingdomId(mainServices.findOneUser(userId).getKingdom().getKingdomId());
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
