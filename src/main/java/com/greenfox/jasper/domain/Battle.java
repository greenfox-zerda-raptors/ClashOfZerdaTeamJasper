package com.greenfox.jasper.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="battle_result_table")
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "battle_id")
    private long battleId;
    @Column(name = "attacker_kingdom")
    private Kingdom attacker;
    @Column(name = "defender_kingdom")
    private Kingdom defender;
    @Transient
    private List<Troop> attackerTroops;
    @Transient
    private List<Troop> defenderTroops;
    @Transient
    private int attackerHp;
    @Transient
    private int defenderHp;
    @Column(name = "attacker_attack_power")
    private int attackerAttackPower;
    @Column(name = "defender_attack_power")
    private int defenderAttackPower;
    @Column(name = "attacker_defense_power")
    private int attackerDefensePower;
    @Column(name = "defender_defense_power")
    private int defenderDefensePower;
    private int attackerDamageDone;
    private int defenderDamageDone;
    private List<Troop> attackerCasualties;
    @Transient
    private List<Troop> defenderCasualties;

    private int buildingDamage;

    public Battle(){

    }

    public Battle (Kingdom attacker, Kingdom defender, List<Troop> attackerTroops, List<Troop> defenderTroops){

        System.out.println("Battle initiated");

        this.attacker = attacker;
        this.defender = defender;
        this.attackerTroops = attackerTroops;
        this.defenderTroops = defenderTroops;
        this.attackerHp = getHp(this.attackerTroops);
        this.defenderHp = getHp(this.defenderTroops);
        this.attackerAttackPower = getAttackPower(this.attackerTroops);
        this.defenderAttackPower = getAttackPower(this.defenderTroops);
        this.attackerDefensePower = getDefensePower(this.attackerTroops);
        this.defenderDefensePower = getDefensePower(this.defenderTroops);
        this.attackerDamageDone = this.attackerAttackPower - this.defenderDefensePower;
        this.defenderDamageDone = this.defenderAttackPower - this.attackerDefensePower;
        this.attackerCasualties = getCasualties(
                this.attackerTroops,
                this.defenderAttackPower
        );
        this.defenderCasualties = getCasualties(
                this.defenderTroops,
                this.attackerAttackPower
        );
        this.attackerTroops.removeAll(attackerCasualties);
        this.defenderTroops.removeAll(defenderCasualties);
    }

    public int getHp(List<Troop> troops){
        int hp = 0;
        for (Troop troop : troops){
            hp += troop.getHp();
        }
        return hp;
    }

    public int getAttackPower(List<Troop> troops) {
        int attackPower = 0;
        for (Troop troop : troops){
            attackPower += troop.getAttack();
        }
        return attackPower;
    }

    public int getDefensePower(List<Troop> troops) {
        int defensePower = 0;
        for (Troop troop : troops){
            defensePower += troop.getDefense();
        }
        return defensePower;
    }

    public List<Troop> getCasualties(List<Troop> friendlyInitialTroops, int foeAttack){

        List<Troop> friendlyTroops = new ArrayList<>(friendlyInitialTroops);

        List<Troop> casualties = new ArrayList<>();

        int friendlyHp = getHp(friendlyTroops);

        int friendlyDefense = getDefensePower(friendlyTroops);

        int totalHpLoss = foeAttack - friendlyDefense;

        if(friendlyHp <= totalHpLoss){

            this.buildingDamage = totalHpLoss - friendlyHp;

            for (Troop troop : friendlyTroops){
                troop.setHp(0);
            }

            casualties.addAll(friendlyTroops);

        }else{
                    for (Troop troop : friendlyTroops) {

                        int topTroopinitialHp = troop.getHp();

                        troop.setHp(troop.getHp() - totalHpLoss);

                        totalHpLoss -= topTroopinitialHp;

                        if (totalHpLoss < 0) {
                            totalHpLoss = 0;
                        }

                        if (troop.getHp() <= 0) {
                            casualties.add(troop);
                        }

                    }
        }
        return casualties;
    }

    public int getAttackerDamageDone() {
        return attackerDamageDone;
    }

    public int getDefenderDamageDone() {
        return defenderDamageDone;
    }

    public int getBuildingDamage() {
        return buildingDamage;
    }

    public long getBattleId() {
        return battleId;
    }

    public void setBattleId(long battleId) {
        this.battleId = battleId;
    }

    public Kingdom getAttacker() {
        return attacker;
    }

    public void setAttacker(Kingdom attacker) {
        this.attacker = attacker;
    }

    public Kingdom getDefender() {
        return defender;
    }

    public void setDefender(Kingdom defender) {
        this.defender = defender;
    }

    public List<Troop> getAttackerTroops() {
        return attackerTroops;
    }

    public void setAttackerTroops(List<Troop> attackerTroops) {
        this.attackerTroops = attackerTroops;
    }

    public List<Troop> getDefenderTroops() {
        return defenderTroops;
    }

    public void setDefenderTroops(List<Troop> defenderTroops) {
        this.defenderTroops = defenderTroops;
    }

    public int getAttackerHp() {
        return attackerHp;
    }

    public void setAttackerHp(int attackerHp) {
        this.attackerHp = attackerHp;
    }

    public int getDefenderHp() {
        return defenderHp;
    }

    public void setDefenderHp(int defenderHp) {
        this.defenderHp = defenderHp;
    }

    public int getAttackerAttackPower() {
        return attackerAttackPower;
    }

    public void setAttackerAttackPower(int attackerAttackPower) {
        this.attackerAttackPower = attackerAttackPower;
    }

    public int getDefenderAttackPower() {
        return defenderAttackPower;
    }

    public void setDefenderAttackPower(int defenderAttackPower) {
        this.defenderAttackPower = defenderAttackPower;
    }

    public int getAttackerDefensePower() {
        return attackerDefensePower;
    }

    public void setAttackerDefensePower(int attackerDefensePower) {
        this.attackerDefensePower = attackerDefensePower;
    }

    public int getDefenderDefensePower() {
        return defenderDefensePower;
    }

    public void setDefenderDefensePower(int defenderDefensePower) {
        this.defenderDefensePower = defenderDefensePower;
    }

    public List<Troop> getAttackerCasualties() {
        return attackerCasualties;
    }

    public void setAttackerCasualties(List<Troop> attackerCasualties) {
        this.attackerCasualties = attackerCasualties;
    }

    public List<Troop> getDefenderCasualties() {
        return defenderCasualties;
    }

    public void setDefenderCasualties(List<Troop> defenderCasualties) {
        this.defenderCasualties = defenderCasualties;
    }

    public void setAttackerDamageDone(int attackerDamageDone) {
        this.attackerDamageDone = attackerDamageDone;
    }

    public void setDefenderDamageDone(int defenderDamageDone) {
        this.defenderDamageDone = defenderDamageDone;
    }

    public void setBuildingDamage(int buildingDamage) {
        this.buildingDamage = buildingDamage;
    }
}
