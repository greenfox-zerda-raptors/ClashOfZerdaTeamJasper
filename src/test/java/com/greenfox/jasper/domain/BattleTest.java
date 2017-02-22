package com.greenfox.jasper.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class BattleTest {

    Battle testBattle = new Battle();

    Kingdom testAttacker = new Kingdom();

    Kingdom testDefender = new Kingdom();

    List<Troop> testWithOneTroop = new ArrayList<Troop>() {{
            add(new Troop());
    }};

    List<Troop> testWithTwoTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 2; i++) {
            add(new Troop(i));
        }
    }};

    List<Troop> testWithThreeTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 3; i++) {
            add(new Troop(i));
        }
    }};

    List<Troop> testWithFiveTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 5; i++) {
            add(new Troop(i));
        }
    }};

    List<Troop> testWithFiveTroopsEnemy = new ArrayList<Troop>(){{
        for(int i = 0; i < 5; i++) {
            add(new Troop(i));
        }
    }};

    List<Troop> testWithTenTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 10; i++) {
            add(new Troop(i));
        }
    }};

    @Test
    public void GetHpTest() throws Exception {

        int actualHp = testBattle.getHp(testWithFiveTroops);

        assertEquals(100, actualHp);
    }

    @Test
    public void GetAttackTest() throws Exception {

        int actualAttack = testBattle.getAttackPower(testWithFiveTroops);

        assertEquals(50, actualAttack);
    }

    @Test
    public void GetDefenseTest() throws Exception {

        int actualDefense = testBattle.getDefensePower(testWithFiveTroops);

        assertEquals(25, actualDefense);
    }


    @Test
    public void GetCasualtiesZeroAttackerVersusOneDefendersTest() throws Exception {
        List<Troop> testCasualties = testBattle.getCasualties(testWithOneTroop, 0);

        assertEquals(0, testCasualties.size());
    }


    @Test
    public void GetCasualtiesOneAttackerVersusOneDefendersTest() throws Exception {
        List<Troop> testCasualties = testBattle.getCasualties(testWithOneTroop, 10);

        assertEquals(0, testCasualties.size());
    }


    @Test
    public void GetCasualtiesThreeAttackerVersusOneDefendersTest() throws Exception {
        List<Troop> testCasualties = testBattle.getCasualties(testWithOneTroop, 30);

        assertEquals(1, testCasualties.size());

    }

    @Test
    public void GetCasualtiesZeroAttackerVersusFiveDefendersTest() throws Exception {
        List<Troop> testCasualties = testBattle.getCasualties(testWithFiveTroops, 0);

            assertEquals(0, testCasualties.size());
    }

    @Test
    public void GetCasualtiesOneAttackerVersusFiveDefendersTest() throws Exception {
        List<Troop> testCasualties = testBattle.getCasualties(testWithFiveTroops, 10);

        assertEquals(0, testCasualties.size());
    }

    @Test
    public void GetCasualtiesTwoAttackerVersusFiveDefendersTest() throws Exception {
        List<Troop> testCasualties = testBattle.getCasualties(testWithFiveTroops, 20);

        assertEquals(0, testCasualties.size());
    }

    @Test
    public void GetCasualtiesThreeAttackerVersusThreeDefendersTest() throws Exception {
        List<Troop> testCasualties = testBattle.getCasualties(testWithThreeTroops, 30);

        assertEquals(0, testCasualties.size());
    }

    @Test
    public void GetCasualtiesFiveAttackerVersusFiveDefendersTest() throws Exception {
        List<Troop> testCasualties = testBattle.getCasualties(testWithFiveTroops, 50);

        assertEquals(1, testCasualties.size());
    }

    @Test
    public void GetDefenderCasualtiesFiveAttackerVersusFiveDefendersTest() throws Exception {

        Battle battleTest = new Battle(testAttacker, testDefender, testWithFiveTroops, testWithFiveTroopsEnemy);

        assertEquals(1, battleTest.getDefenderCasualties().size());
    }

    @Test
    public void GetAttackerCasualtiesFiveAttackerVersusFiveDefendersTest() throws Exception {

        Battle battleTest = new Battle(testAttacker, testDefender, testWithFiveTroops, testWithFiveTroopsEnemy);

        assertEquals(1, battleTest.getAttackerCasualties().size());
    }

}