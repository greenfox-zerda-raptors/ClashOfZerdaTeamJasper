package com.greenfox.jasper.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Table(name = "users")
@Component
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String name;
    private String kingdom;
    private int points;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Building> buildings;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Troop> troops;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Resource> resources;

    public User() {
    }

    public User(String name, String kingdom) {
        this.name = name;
        this.kingdom = kingdom;
        this.points = 0;
    }

    public Collection<Resource> getResources() {
        return resources;
    }

    public void setResources(Collection<Resource> resources) {
        this.resources = resources;
    }

    public Collection<Troop> getTroops() {
        return troops;
    }

    public void setTroops(Collection<Troop> troops) {
        this.troops = troops;
    }

    public Collection<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Collection<Building> buildings) {
        this.buildings = buildings;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

