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
    @Transient
    private List<Troop> attackerCasualties;
    @Transient
    private List<Troop> defenderCasualties;
    @Column(name="defender_losses")
    private int defenderLosses;
    @Column(name = "attacker_losses")
    private int attackerLosses;

    public Battle (Kingdom attacker, Kingdom defender, List<Troop> attackerTroops){
        this.attacker = attacker;
        this.defender = defender;
        this.attackerTroops = attackerTroops;
        this.defenderTroops = getDefenderTroops(defender);
        this.attackerHp = getHp(this.attackerTroops);
        this.defenderHp = getHp(this.defenderTroops);
        this.attackerAttackPower = getAttackPower(this.attackerTroops);
        this.defenderAttackPower = getAttackPower(this.defenderTroops);
        this.attackerDefensePower = getDefensePower(this.attackerTroops);
        this.defenderDefensePower = getDefensePower(this.defenderTroops);
        this.attackerCasualties = getCasualties(
                this.attackerTroops,
                this.attackerHp,
                this.defenderAttackPower,
                this.attackerDefensePower
        );
        this.defenderCasualties = getCasualties(
                this.defenderTroops,
                this.defenderHp,
                this.attackerAttackPower,
                this.defenderDefensePower
        );
    }

    private List<Troop> getDefenderTroops(Kingdom defender){
        List<Troop> defenderTroops = new ArrayList<>();
        for (Troop troop : defender.getTroops()){
            defenderTroops.add(troop);
        }
        return defenderTroops;
    }

    private int getHp(List<Troop> troops){
        int hp = 0;
        for (Troop troop : troops){
            hp += troop.getHp();
        }
        return hp;
    }

    private int getAttackPower(List<Troop> troops) {
        int attackPower = 0;
        for (Troop troop : troops){
            attackPower += troop.getAttack();
        }
        return attackPower;
    }

    private int getDefensePower(List<Troop> troops) {
        int defensePower = 0;
        for (Troop troop : troops){
            defensePower += troop.getDefense();
        }
        return defensePower;
    }

    private List<Troop> getCasualties(List<Troop> friendlyTroops, int friendlyHp, int foeAttack, int friendlyDefense){
        List<Troop> casualties = new ArrayList<>();
        int totalHpLoss = foeAttack - friendlyDefense;
        if(friendlyHp <= totalHpLoss){
            casualties.addAll(friendlyTroops);
        }else{
            while(totalHpLoss > 0){
                for (Troop troop : friendlyTroops){
                    if (totalHpLoss >= troop.getHp()){
                        casualties.add(troop);
                        totalHpLoss -= troop.getHp();
                    }else{
                        troop.setHp(troop.getHp() - totalHpLoss);
                        totalHpLoss = 0;
                    }
                }
            }
        }
        return casualties;
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

    public int getDefenderLosses() {
        return defenderLosses;
    }

    public void setDefenderLosses(int defenderLosses) {
        this.defenderLosses = defenderLosses;
    }

    public int getAttackerLosses() {
        return attackerLosses;
    }

    public void setAttackerLosses(int attackerLosses) {
        this.attackerLosses = attackerLosses;
    }
}
