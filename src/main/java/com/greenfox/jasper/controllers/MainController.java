package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/", method = RequestMethod.GET)
public class MainController {

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public User helloHeroku(){
        return userRepo.findOne((long)1);
    }
}
