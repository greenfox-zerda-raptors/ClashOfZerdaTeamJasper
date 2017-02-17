package com.greenfox.jasper.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class WarTest {

    List<Troop> testArmyOfFiveTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 5; i++) {
            add(new Troop());
        }
    }};

    List<Troop> testArmyOfSixTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 6; i++) {
            add(new Troop());
        }
    }};

    Kingdom testAttacker = new Kingdom();

    Kingdom testDefender = new Kingdom();

    Battle testBattle = new Battle(testAttacker, testDefender, testArmyOfFiveTroops, testArmyOfFiveTroops);

    War testWar = new War();

    @Test
    public void repeatAttackOnceTest() throws Exception {

        assertEquals(0, testWar.repeatAttack(testBattle).getAttackerCasualties().size());

    }

    @Test
    public void repeatAttackThriceTest() throws Exception {

        for(int i = 0; i < 3; i++){
           testBattle = testWar.repeatAttack(testBattle);
        }

        assertEquals(2, testWar.repeatAttack(testBattle).getAttackerCasualties().size());

    }

    @Test
    public void doWarTest() throws Exception {

    }

    @Test
    public void migrateTroopIdsTest() throws Exception {

    }

}