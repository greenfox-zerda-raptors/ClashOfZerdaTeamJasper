package com.greenfox.jasper.controllers;

import com.google.gson.Gson;
import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/", method = RequestMethod.GET)
public class MainController {

    @Autowired
    MainServices mainServices;

    @RequestMapping(value = "/kingdom/{kingdomName}", method = RequestMethod.GET)
    public User getKingdom(@PathVariable String kingdomName){
        return mainServices.findKingdom(kingdomName);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable int userId){
        return mainServices.findOneUser(userId);
    }
}


