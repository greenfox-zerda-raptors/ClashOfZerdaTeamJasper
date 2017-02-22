package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.War;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.dto.BattleRequestDto;
import com.greenfox.jasper.dto.BattleResponseDto;
import com.greenfox.jasper.security.JwtUser;
import com.greenfox.jasper.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kingdom/battles")
public class BattleController {

    @Autowired
    private BattleServices battleServices;

    @Autowired
    private DTOServices dtoServices;

    @Autowired
    private TroopServices troopServices;

    @Autowired
    private KingdomServices kingdomServices;

    @Autowired
    private TimedEventServices timedEventServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity listBattles() {
        return ResponseEntity.status(HttpStatus.OK).body("Battles are not implemented yet");
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity getOneBattle() {
        return ResponseEntity.status(HttpStatus.OK).body("Battles are not implemented yet");
    }

    // TODO battle history for user/kigndom - GET "kingdom/battles"

    // TODO one battle for user/kingdom - GET "kingdom/battles/{id}"
    @RequestMapping(value = "/attack", method = RequestMethod.POST)
    public ResponseEntity<BattleResponseDto> attackOneKingdom(@RequestBody BattleRequestDto requestDto) {

        War war = new War();

        BattleResponseDto result = dtoServices.convertBattleToDto(war.doWar(battleServices.doBattle(requestDto)));

        troopServices.deleteAllFromList(result.getLostAttackerTroopIds());
        troopServices.deleteAllFromList(result.getLostDefenderTroopIds());



        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/testing", method = RequestMethod.POST)
    public ResponseEntity attackTest(@AuthenticationPrincipal JwtUser currentUser, @RequestBody BattleRequestDto requestDto){
        Kingdom dummy = kingdomServices.findOneByUserId(currentUser.getId());

        if(dummy == null){
            return new ResponseEntity("No such kingdom", HttpStatus.OK);
        }
        timedEventServices.addNewBattleEvent(dummy.getKingdomId(), requestDto.getDefenderTroops(), requestDto.getDefenderId());

        return new ResponseEntity(HttpStatus.OK);
    }
}
