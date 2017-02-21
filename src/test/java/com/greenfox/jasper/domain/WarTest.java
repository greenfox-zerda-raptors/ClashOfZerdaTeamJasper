package com.greenfox.jasper.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class WarTest {

    List<Troop> testArmyOfFiveTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 5; i++) {
            add(new Troop(i));
        }
    }};

    List<Troop> testArmyOfFiveTroopsEnemy = new ArrayList<Troop>() {{
        for(int i = 0; i < 5; i++) {
            add(new Troop(i));
        }
    }};

    List<Troop> testArmyOfSixTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 6; i++) {
            add(new Troop(i));
        }
    }};

    Kingdom testAttacker = new Kingdom();

    Kingdom testDefender = new Kingdom();

    Battle testBattle = new Battle(testAttacker, testDefender, testArmyOfFiveTroops, testArmyOfFiveTroopsEnemy);

    War testWar = new War();

    @Test
    public void repeatAttackOnceTest() throws Exception {

        assertEquals(1, testWar.repeatAttack(testBattle).getAttackerCasualties().size());

    }

    @Test
    public void repeatAttackThriceTest() throws Exception {

        Battle testBattleThree = testWar.repeatAttack(testWar.repeatAttack(testBattle));


        assertEquals(1,testBattleThree.getAttackerCasualties().size());

    }

    @Test
    public void doWarTestDefenderCasualtiesFiveVersusFive() throws Exception {


        testBattle.getAttacker().setUser(new User());

        testBattle.getDefender().setUser(new User());

        testBattle.getAttacker().getUser().setId((long)1);

        testBattle.getDefender().getUser().setId((long)2);

        assertEquals(3, testWar.doWar(testBattle).getLostDefenderTroopIds().size());
    }

    @Test
    public void doWarTestAttackerCasualtiesFiveVersusFive() throws Exception {


        testBattle.getAttacker().setUser(new User());

        testBattle.getDefender().setUser(new User());

        testBattle.getAttacker().getUser().setId((long)2);

        testBattle.getDefender().getUser().setId((long)1);

        assertEquals(3, testWar.doWar(testBattle).getLostAttackerTroopIds().size());
    }

}