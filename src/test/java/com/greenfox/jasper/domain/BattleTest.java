package com.greenfox.jasper.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class BattleTest {

    Battle testBattle = new Battle();

    List<Troop> testWithOneTroop = new ArrayList<Troop>() {{
            add(new Troop());
    }};

    List<Troop> testWithTwoTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 2; i++) {
            add(new Troop());
        }
    }};

    List<Troop> testWithThreeTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 3; i++) {
            add(new Troop());
        }
    }};

    List<Troop> testWithFiveTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 5; i++) {
            add(new Troop());
        }
    }};

    List<Troop> testWithTenTroops = new ArrayList<Troop>() {{
        for(int i = 0; i < 10; i++) {
            add(new Troop());
        }
    }};

    @Test
    public void GetHpTest(){

        int actualHp = testBattle.getHp(testWithFiveTroops);

        assertEquals(100, actualHp);
    }

    @Test
    public void GetAttackTest(){

        int actualAttack = testBattle.getAttackPower(testWithFiveTroops);

        assertEquals(50, actualAttack);
    }

    @Test
    public void GetDefenseTest(){

        int actualDefense = testBattle.getDefensePower(testWithFiveTroops);

        assertEquals(25, actualDefense);
    }


    @Test
    public void GetCasualtiesZeroAttackerVersusOneDefendersTest(){
        List<Troop> testCasualties = testBattle.getCasualties(testWithOneTroop, 0);

        assertEquals(0, testCasualties.size());
    }


    @Test
    public void GetCasualtiesOneAttackerVersusOneDefendersTest(){
        List<Troop> testCasualties = testBattle.getCasualties(testWithOneTroop, 10);

        assertEquals(0, testCasualties.size());
    }


    @Test
    public void GetCasualtiesThreeAttackerVersusOneDefendersTest(){
        List<Troop> testCasualties = testBattle.getCasualties(testWithOneTroop, 30);

        assertEquals(1, testCasualties.size());

    }

    @Test
    public void GetCasualtiesZeroAttackerVersusFiveDefendersTest(){
        List<Troop> testCasualties = testBattle.getCasualties(testWithFiveTroops, 0);

            assertEquals(0, testCasualties.size());
    }

    @Test
    public void GetCasualtiesOneAttackerVersusFiveDefendersTest(){
        List<Troop> testCasualties = testBattle.getCasualties(testWithFiveTroops, 10);

        assertEquals(0, testCasualties.size());
    }

    @Test
    public void GetCasualtiesTeoAttackerVersusFiveDefendersTest(){
        List<Troop> testCasualties = testBattle.getCasualties(testWithFiveTroops, 20);

        assertEquals(0, testCasualties.size());
    }

    @Test
    public void GetCasualtiesFIveAttackerVersusFiveDefendersTest(){
        List<Troop> testCasualties = testBattle.getCasualties(testWithFiveTroops, 50);

        assertEquals(1, testCasualties.size());
    }

}