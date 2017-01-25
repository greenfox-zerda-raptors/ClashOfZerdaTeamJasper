package com.greenfox.jasper.Models.GameItem;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Created by almasics on 2017.01.20..
 */

@Entity
@DiscriminatorValue("Mine")
public class Mine extends Building {

    @Transient
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
