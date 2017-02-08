package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Realm;
import com.greenfox.jasper.dto.BattleResultResponse;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.KingdomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by almasics on 2017.02.08..
 */
@RestController
public class BattleController {

    @Autowired
    Realm battleRealm;

    @Autowired
    private DTOServices dtoServices;
    @Autowired
    KingdomServices kingdomServices;

    @RequestMapping(value = "/battle", method = RequestMethod.GET)
    public ResponseEntity<BattleResultResponse> getBattleResult(HttpServletResponse response) {

        BattleResultResponse result = new BattleResultResponse(dtoServices.BattleResultToDTO(battleRealm.battle(kingdomServices.findKingdomByName("romania"), kingdomServices.findKingdomByName("austria"))));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
