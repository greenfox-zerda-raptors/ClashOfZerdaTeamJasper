package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Resource;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.services.EventServices;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kingdom/{userId}/resources", method = RequestMethod.GET)
public class ResourceController {

    @Autowired
    private MainServices mainServices;

    @Autowired
    private EventServices eventServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Resource> getResources(@PathVariable int userId) {
        return mainServices.findAllResourcesByKingdomId(mainServices.findOneUser(userId).getKingdom().getKingdomId());
    }

    @RequestMapping(value = "/{resourceId}", method = RequestMethod.GET)
    public Resource getOneTroop(@PathVariable String resourceId) {
        return mainServices.findOneResource(resourceId);
    }


}
