package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Realm;
import com.greenfox.jasper.services.KingdomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by almasics on 2017.02.08..
 */
@RestController
public class BattleController {

    @Autowired
    private Realm battleRealm;

    @Autowired
    KingdomServices kingdomServices;

    @RequestMapping(value = "/battle", method = RequestMethod.GET)
    public String executeBattle() {
        return (battleRealm.battle(kingdomServices.findKingdomByName("romania"), kingdomServices.findKingdomByName("austria")).battleToString());
    }
}
