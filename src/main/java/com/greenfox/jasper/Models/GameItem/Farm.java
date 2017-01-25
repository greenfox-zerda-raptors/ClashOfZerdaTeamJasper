package com.greenfox.jasper.Models.GameItem;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Created by almasics on 2017.01.20..
 */
@Entity
@DiscriminatorValue("Farm")

public class Farm extends Building {
    @Transient
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