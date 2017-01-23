package com.greenfox.jasper.Models.GameItem;

import javax.persistence.*;

/**
 * Created by almasics on 2017.01.20..
 */
@Entity
@Table(name = "Buildings")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    int buildingLevel = 1;

    long constructionTime;
    int baseCost;
    int buildingUpgradeCost;
    long buildingUpgradeTime;
    long endUpgradeTime = buildingUpgradeTime + System.currentTimeMillis();


    public Building(){

    }



    public void levelUp(){
        this.buildingLevel++;
    }

    public void demolish(){
        if(buildingLevel > 1) {
            this.buildingLevel--;
        }
    }


    public long getId() {
        return id;
    }

    public long getConstructionTime() {
        return constructionTime;
    }

    public void setConstructionTime(long constructionTime) {
        this.constructionTime = constructionTime;
    }

    public int getBuildingLevel() {
        return buildingLevel;
    }

    public void setBuildingLevel(int buildingLevel) {
        this.buildingLevel = buildingLevel;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public int getBuildingUpgradeCost() {
        return buildingUpgradeCost;
    }

    public void setBuildingUpgradeCost(int buildingUpgradeCost) {
        this.buildingUpgradeCost = buildingUpgradeCost;
    }

    public String buildingToString() {
        return String.format("building details: %d level", buildingLevel);
    }

    public long getBuildingUpgradeTime() {
        return buildingUpgradeTime;
    }

    public void setBuildingUpgradeTime(long buildingUpgradeTime) {
        this.buildingUpgradeTime = buildingUpgradeTime;
    }

    public long getEndUpgradeTime() {
        return endUpgradeTime;
    }

    public void setEndUpgradeTime(long endUpgradeTime) {
        this.endUpgradeTime = endUpgradeTime;
    }
}
