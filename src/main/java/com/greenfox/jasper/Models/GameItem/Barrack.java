package com.greenfox.jasper.Models.GameItem;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Created by almasics on 2017.01.20..
 */

@Entity
@DiscriminatorValue("Barrack")

public class Barrack extends Building {

    @Transient
    private long unitBuildTime = 60000 / buildingLevel;

    public Barrack() {
        super();
        this.constructionTime = 30000;
        this.baseCost = 100;
        this.unitBuildTime = 10000 / buildingLevel;
        this.buildingUpgradeCost = 100;
        this.buildingUpgradeTime = 600000 / buildingLevel;
    }

    public long getUnitBuildTime() {
        return unitBuildTime;
    }

    public void setUnitBuildTime(long unitBuildTime) {
        this.unitBuildTime = unitBuildTime;
    }
}
