package com.greenfox.jasper.domain;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resources")
@Component
public class Resource implements Serializable {

    @Id
    private String type;
    private int amount;
    private ArrayList<Building> buildings;

    public Resource() {
    }

    public Resource(String type) {
        this.type = type;
        this.amount = 1;
        buildings = new ArrayList<>();
        buildings.add(new Building("farm"));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }
}
