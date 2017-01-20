package com.greenfox.jasper.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/", method = RequestMethod.GET)
public class MainController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String helloHeroku(){
        return "hello heroku!";
    }
}
