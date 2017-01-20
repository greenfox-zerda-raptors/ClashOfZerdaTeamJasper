package com.greenfox.jasper.model;

/**
 * Created by almasics on 2017.01.20..
 */
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