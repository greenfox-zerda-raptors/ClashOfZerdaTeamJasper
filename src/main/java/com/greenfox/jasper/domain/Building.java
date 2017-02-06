package com.greenfox.jasper.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "building_table")
@Component
public class Building implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "building_id")
    private long buildingId;

    @Column(name = "building_type")
    private String type;

    @Column(name = "building_level")
    private int level;
    private int hp;

    @ManyToOne
    @JsonIgnore
    private Kingdom kingdom;
    @ManyToOne
    @JsonIgnore
    private Resource resource;

    public Building() {
    }

    public Building(String type) {
        this.type = type;
        this.level = 0;
        this.hp = 100;
    }

    public Building(String type, Kingdom kingdom) {
        this.type = type;
        this.level = 0;
        this.hp = 100;
        this.kingdom = kingdom;
    }

    public Building(String type, Kingdom kingdom, int level) {
        this(type, kingdom);
        this.level = level;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void levelUp() {
//        TODO formula for stat changes due to lvl
        this.level++;
    }

    public void decreaseLvl() {
        if(level > 1) {
            this.level--;
        }
    }

}
