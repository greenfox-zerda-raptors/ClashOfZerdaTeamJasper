package com.greenfox.jasper.model;

import javax.persistence.*;

/**
 * Created by almasics on 2017.01.20..
 */

@Entity
@DiscriminatorValue("Mine")
public class Mine extends Building {
    protected int goldPerMinute = 10 * buildingLevel;

    public Mine() {
        super();
        this.constructionTime = 15000;
        this.baseCost = 40;
        this.buildingUpgradeCost = 40;
        this.buildingUpgradeTime = 10000;

    }

    public int getGoldPerMinute() {
        return goldPerMinute;
    }

    public void setGoldPerMinute(int goldPerMinute) {
        this.goldPerMinute = goldPerMinute;
    }
}
