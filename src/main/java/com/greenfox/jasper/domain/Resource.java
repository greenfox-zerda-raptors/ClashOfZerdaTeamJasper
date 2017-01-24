package com.greenfox.jasper.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "resources")
@Component
public class Resource implements Serializable {

    @Id
    private String type;
    private int amount;
    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    private Collection<Building> buildings;
    @ManyToOne
    @JsonIgnore
    private User user;

    public Resource() {
    }

    public Resource(String type) {
        this.type = type;
        this.amount = 1;
    }

    public Resource(String type, User user) {
        this.user = user;
        this.type = type;
        this.amount = 1;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Collection<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Collection<Building> buildings) {
        this.buildings = buildings;
    }
}
