package com.greenfox.jasper.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by almasics on 2017.01.20..
 */

@Entity
@DiscriminatorValue("Barracks")

public class Barracks extends Building {

    private long unitBuildTime;

    public Barracks() {
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
