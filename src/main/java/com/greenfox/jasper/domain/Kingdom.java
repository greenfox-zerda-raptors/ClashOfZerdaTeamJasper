package com.greenfox.jasper.domain;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kingdoms")
@Component
public class Kingdom implements Serializable {

    @Id
    private Long userId;
    private ArrayList<Building> buildings;
    private ArrayList<Resource> resources;
    private ArrayList<Troop> troops;

    public Kingdom() {
    }

    public Kingdom(Long userId){
        this.userId = userId;
        this.buildings = new ArrayList<>();
        buildings.add(new Building("farm"));
        this.resources = new ArrayList<>();
        resources.add(new Resource("food"));
        this.troops = new ArrayList<>();
        troops.add(new Troop());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBuildings() {
        String buildingsJson = new Gson().toJson(buildings);
        return buildingsJson;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public String getResources() {
        String resourcesJson = new Gson().toJson(resources);
        return resourcesJson;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public String getTroops() {
        String troopsJson = new Gson().toJson(troops);
        return troopsJson;
    }

    public void setTroops(ArrayList<Troop> troops) {
        this.troops = troops;
    }
}


