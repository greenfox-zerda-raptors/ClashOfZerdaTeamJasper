package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.dto.BattleRequestDto;
import com.greenfox.jasper.dto.BattleResponseDto;
import com.greenfox.jasper.services.BattleServices;
import com.greenfox.jasper.services.DTOServices;
import com.greenfox.jasper.services.TroopServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by almasics on 2017.02.08..
 */
@RestController
@RequestMapping("/kingdom/attack")
public class BattleController {

    @Autowired
    BattleServices battleServices;

    @Autowired
    DTOServices dtoServices;

    @Autowired
    TroopServices troopServices;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<BattleResponseDto> attackOneKingdom(@RequestBody BattleRequestDto requestDto){

        BattleResponseDto result = dtoServices.convertBattleToDto(battleServices.doBattle(requestDto));

        troopServices.deleteAllFromList(result.getAttackerCasualties());
        troopServices.deleteAllFromList(result.getDefenderCasualties());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
