package com.greenfox.jasper.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "buildings")
@Component
public class Building implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long buildingId;
    private String type;
    private int level;
    private int hp;
    @ManyToOne
    @JsonIgnore
    private User user;
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

    public Building(String type, User user, Resource resource) {
        this.type = type;
        this.level = 0;
        this.hp = 100;
        this.user = user;
        this.resource = resource;
    }



    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void levelUp() {
        this.level++;
    }

    public void decreaseLvl() {
        this.level--;
    }
}
