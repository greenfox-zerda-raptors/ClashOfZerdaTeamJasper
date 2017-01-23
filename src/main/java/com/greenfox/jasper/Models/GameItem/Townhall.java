package com.greenfox.jasper.Models.GameItem;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by almasics on 2017.01.20..
 */
@Entity
@DiscriminatorValue("Townhall")
public class Townhall extends Building {
    protected int goldPerMinute = 10*buildingLevel;
    protected int foodPerMinute = 10 * buildingLevel;

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