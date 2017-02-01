package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kingdom", method = RequestMethod.GET)
public class KingdomController {

    @Autowired
    private MainServices mainServices;

    @RequestMapping(value = "/{kingdomName}", method = RequestMethod.GET)
    public Kingdom getKingdom(@PathVariable String kingdomName) {
        return mainServices.findKingdomByName(kingdomName);
    }
}
