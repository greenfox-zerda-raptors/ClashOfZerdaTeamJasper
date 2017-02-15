package com.greenfox.jasper.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kingdom_table")
@Component
public class Kingdom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kingdom_id")
    private long kingdomId;

    @Column(name = "kingdom_name")
    private String name;

    @JsonManagedReference
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "kingdom", cascade = CascadeType.ALL)
    private List<Building> buildings;

    @OneToMany(mappedBy = "kingdom", cascade = CascadeType.ALL)
    private List<Troop> troops;

    @OneToMany(mappedBy = "kingdom", cascade = CascadeType.ALL)
    private List<Resource> resources;

    @Column(name="last_update_time")
    private long updateTime;

    @Column(name = "pos_x")
    private int posX;

    @Column(name = "pos_y")
    private int posY;

    public Kingdom() {
        this.name = "";
        this.buildings = new ArrayList<>();
        this.resources = new ArrayList<>();
         this.troops = new ArrayList<>();
        this.updateTime = System.currentTimeMillis();
    }

    public Kingdom(String name, User user) {
        this.name = name;
        this.user = user;
        this.buildings = new ArrayList<>();
        this.resources = new ArrayList<>();
        this.troops = new ArrayList<>();
        this.updateTime = System.currentTimeMillis();
    }

    public long getKingdomId() {
        return kingdomId;
    }

    public void setKingdomId(long kingdomId) {
        this.kingdomId = kingdomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
