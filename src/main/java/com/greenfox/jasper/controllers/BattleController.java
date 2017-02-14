package com.greenfox.jasper.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by almasics on 2017.02.08..
 */
@RestController
@RequestMapping(value = "/kingdom/battles")
public class BattleController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity listBattles(){
        return ResponseEntity.status(HttpStatus.OK).body("Battles are not implemented yet");
    }
    @RequestMapping(value = "/{id}")
    public ResponseEntity getOneBattle(){
        return ResponseEntity.status(HttpStatus.OK).body("Battles are not implemented yet");
    }

    // TODO battle history for user/kigndom - GET "kingdom/battles"

    // TODO one battle for user/kingdom - GET "kingdom/battles/{id}"
}
