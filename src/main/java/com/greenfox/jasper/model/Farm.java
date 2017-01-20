package com.greenfox.jasper.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by almasics on 2017.01.20..
 */
@Entity
@DiscriminatorValue("Farm")

public class Farm extends Building {
    protected int foodPerMinute = 10 * buildingLevel;

    public Farm() {
        super();
        this.constructionTime = 15000;
        this.baseCost = 30;
        this.buildingUpgradeCost = 30;
        this.buildingUpgradeTime = 100000;

    }


    public int getFoodPerMinute() {
        return foodPerMinute;
    }

    public void setFoodPerMinute(int foodPerMinute) {
        this.foodPerMinute = foodPerMinute;
    }
}