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

   List<Troop> testArmyOfTwentyTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 20; i++) {
            add(new Troop(i));
        }
    }};

    Kingdom testAttacker = new Kingdom();

    Kingdom testDefender = new Kingdom();

    Battle testBattle5v5 = new Battle(testAttacker, testDefender, testArmyOfFiveTroops, testArmyOfFiveTroopsEnemy);

    Battle testBattle20v5 = new Battle(testAttacker, testDefender, testArmyOfTwentyTroops, testArmyOfSixTroops);

    War testWar = new War();

    @Test
    public void repeatAttackOnceTest() throws Exception {

        assertEquals(1, testWar.repeatAttack(testBattle5v5).getAttackerCasualties().size());

    }

    @Test
    public void repeatAttackThriceTest() throws Exception {

        Battle testBattleThree = testWar.repeatAttack(testWar.repeatAttack(testBattle5v5));


        assertEquals(1,testBattleThree.getAttackerCasualties().size());

    }

    @Test
    public void doWarTestDefenderCasualtiesFiveVersusFive() throws Exception {


        testBattle5v5.getAttacker().setUser(new User());

        testBattle5v5.getDefender().setUser(new User());

        testBattle5v5.getAttacker().getUser().setId((long)1);

        testBattle5v5.getDefender().getUser().setId((long)2);

        assertEquals(3, testWar.doWar(testBattle5v5).getLostDefenderTroopIds().size());
    }

    @Test
    public void doWarTestAttackerCasualtiesFiveVersusFive() throws Exception {


        testBattle5v5.getAttacker().setUser(new User());

        testBattle5v5.getDefender().setUser(new User());

        testBattle5v5.getAttacker().getUser().setId((long)2);

        testBattle5v5.getDefender().getUser().setId((long)1);

        assertEquals(3, testWar.doWar(testBattle5v5).getLostAttackerTroopIds().size());
    }


    @Test
    public void doWarTestBuildingCasualtiesTwentyVersusFive() throws Exception {


        testBattle20v5.getAttacker().setUser(new User());

        testBattle20v5.getDefender().setUser(new User());

        testBattle20v5.getAttacker().getUser().setId((long)2);

        testBattle20v5.getDefender().getUser().setId((long)1);

        List<Building> testBuildings = new ArrayList<Building>() {{
            for(int i = 0; i < 5; i++) {
                add(new Building("mine"));
            }
        }};

        testBattle20v5.getDefender().setBuildings(testBuildings);

        assertEquals(0, testWar.doWar(testBattle20v5).getDefenderLostBuildingIds().size());
    }

}