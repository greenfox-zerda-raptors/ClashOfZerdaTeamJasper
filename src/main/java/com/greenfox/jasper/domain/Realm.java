package com.greenfox.jasper.domain;

import com.greenfox.jasper.dto.BattleResultDto;
import com.greenfox.jasper.services.KingdomServices;
import com.greenfox.jasper.services.TroopServices;
import com.greenfox.jasper.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by almasics on 2017.02.08..
 */
@Component
public class Realm {

    private Kingdom attacker;
    private Kingdom defender;

    @Autowired
    UserServices userServices;

    @Autowired
    TroopServices troopServices;

    @Autowired
    KingdomServices kingdomServices;

    public BattleResultDto battle(Kingdom attacker, Kingdom defender) {
        List<Troop> attackingTroops = troopServices.findAllTroopsByKingdomName(attacker.getName());
        List<Troop> defendingTroops = troopServices.findAllTroopsByKingdomName(defender.getName());
        ArmyStats currentAttacker = getArmyStats(attackingTroops, attacker);
        ArmyStats currentDefender = getArmyStats(defendingTroops, defender);
        int currentHealthOfAttacker = currentAttacker.getTotalHp();
        int currentHealthOfDefender = currentDefender.getTotalHp();
        int currentAttackerDamage = currentAttacker.getTotalAttack();
        int currentDefenderDamage = currentDefender.getTotalAttack();
        int damageDoneByAttacker = 0;
        int damageDoneByDefender = 0;


        while (currentHealthOfAttacker >= 0 && currentHealthOfDefender >= 0) {

            int damageAgainstDefender = currentAttackerDamage - currentDefender.getTotalDefense();
            int damageAgainstAttacker = currentDefenderDamage - currentAttacker.getTotalDefense();
            currentHealthOfDefender -= damageAgainstDefender;
            currentHealthOfAttacker -= damageAgainstAttacker;
            damageDoneByAttacker += damageAgainstDefender;
            damageDoneByDefender += damageAgainstAttacker;
        }

        return new BattleResultDto(attacker.getName(), defender.getName(), damageDoneByAttacker, damageDoneByDefender);
    }

    public ArmyStats getArmyStats(List<Troop> attackingTroops, Kingdom kingdom) {


        int totalHealth = 0;
        int totalDamage = 0;
        int totalDefense = 0;
        for (Troop troop : attackingTroops) {
            totalHealth += troop.getHp();
            totalDamage += troop.getAttack();
            totalDefense += troop.getDefense();
        }
        return new ArmyStats(totalHealth, totalDamage, totalDefense, kingdom);
    }

}
