package com.greenfox.jasper.model;

/**
 * Created by almasics on 2017.01.20..
 */
public class Townhall extends Building {
    protected int goldPerMinute = 10;
    protected int foodPerMinute = 10;

    public Townhall() {

    }

    public int getGoldPerMinute() {
        return goldPerMinute;
    }

    public void setGoldPerMinute(int goldPerMinute) {
        this.goldPerMinute = goldPerMinute;
    }

    public int getFoodPerMinute() {
        return foodPerMinute;
    }

    public void setFoodPerMinute(int foodPerMinute) {
        this.foodPerMinute = foodPerMinute;
    }
}