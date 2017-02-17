package com.greenfox.jasper.domain;

import java.util.ArrayList;
import java.util.List;

public class War {

    public Battle repeatAttack(Battle firstBattle){

        List<Troop> remainingAttackers = firstBattle.getAttackerTroops();

        remainingAttackers.removeAll(firstBattle.getAttackerCasualties());


        List<Troop> remainingDefenders = firstBattle.getDefenderTroops();

        remainingDefenders.removeAll(firstBattle.getDefenderCasualties());

        return new Battle(firstBattle.getAttacker(), firstBattle.getDefender(), remainingAttackers, remainingDefenders);
    }

    public WarResult doWar(Battle initialBattle) {

        int accumulatedBuildingDamage = 0;

        List<Long> defenderLostBuildingIds = new ArrayList<>();

        List<Long> attackerTroopIds = migrateTroopIds(initialBattle.getAttackerTroops());

        List<Long> defenderTroopIds = migrateTroopIds(initialBattle.getDefenderTroops());

        List<Long> lostAttackerTroopIds = migrateTroopIds(initialBattle.getAttackerCasualties());

        List<Long> lostDefenderTroopIds = migrateTroopIds(initialBattle.getDefenderCasualties());

        int attackerDamageDone = initialBattle.getAttackerDamageDone();

        int defenderDamageDone = initialBattle.getDefenderDamageDone();

        long attackerId = initialBattle.getAttacker().getUser().getId();

        long defenderId = initialBattle.getDefender().getUser().getId();


        for (int i = 0; i < 3; i++) {

            accumulatedBuildingDamage += initialBattle.getBuildingDamage();

            initialBattle = repeatAttack(initialBattle);
        }
        while (accumulatedBuildingDamage > 0) {

            for (Building building : initialBattle.getDefender().getBuildings()) {

                if (accumulatedBuildingDamage >= building.getHp()) {

                    defenderLostBuildingIds.add(building.getBuildingId());

                    accumulatedBuildingDamage -= building.getHp();
                }else{
                    accumulatedBuildingDamage = 0;
                }
            }
        }
        return new WarResult(
                defenderLostBuildingIds,
                attackerTroopIds,
                defenderTroopIds,
                lostAttackerTroopIds,
                lostDefenderTroopIds,
                attackerDamageDone,
                defenderDamageDone,
                attackerId,
                defenderId);
    }


        public List<Long> migrateTroopIds (List<Troop> troops) {

        List<Long> troopIds = new ArrayList<>();

        for(Troop troop : troops){

            troopIds.add(troop.getTroopId());
        }
        return troopIds;
    }

}
