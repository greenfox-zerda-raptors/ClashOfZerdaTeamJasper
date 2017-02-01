package com.greenfox.jasper.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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
    private Collection<Building> buildings;

    @OneToMany(mappedBy = "kingdom", cascade = CascadeType.ALL)
    private Collection<Troop> troops;

    @OneToMany(mappedBy = "kingdom", cascade = CascadeType.ALL)
    private Collection<Resource> resources;

    public Kingdom() {
        this.name = "";
        this.buildings = new ArrayList<>();
        this.resources = new ArrayList<>();
        this.troops = new ArrayList<>();
    }

    public Kingdom(String name, User user) {
        this.name = name;
        this.user = user;
        this.buildings = new ArrayList<>();
        this.resources = new ArrayList<>();
        this.troops = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getKingdomId() {
        return kingdomId;
    }

    public void setKingdomId(long kingdomId) {
        this.kingdomId = kingdomId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Collection<Building> buildings) {
        this.buildings = buildings;
    }

    public Collection<Troop> getTroops() {
        return troops;
    }

    public void setTroops(Collection<Troop> troops) {
        this.troops = troops;
    }

    public Collection<Resource> getResources() {
        return resources;
    }

    public void setResources(Collection<Resource> resources) {
        this.resources = resources;
    }
}
