package com.greenfox.jasper.services;

import com.greenfox.jasper.domain.Battle;
import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.dto.BattleRequestDto;
import com.greenfox.jasper.repos.KingdomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BattleServices {

    @Autowired
    KingdomRepo kingdomRepo;

    @Autowired
    TroopServices troopServices;

    public Battle doBattle (BattleRequestDto battleRequest){

        Kingdom attacker = kingdomRepo.findOne(battleRequest.getAttackerId());

        Kingdom defender = kingdomRepo.findOne(battleRequest.getDefenderId());

        List<Troop> attackerTroops = battleRequest.getAttackerTroops();

        List<Troop> defenderTroops = defender.getTroops();

        return new Battle(attacker, defender, attackerTroops, defenderTroops);
    }
}
