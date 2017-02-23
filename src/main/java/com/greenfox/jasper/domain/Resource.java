package com.greenfox.jasper.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "resource_table")
@Component
public class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resource_id")
    private long id;

    @Column(name = "resource_type")
    private String type;

    @Column(name = "amount")
    private float amount;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    @Transient
    private List<Building> buildings;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "kingdom_id")
    private Kingdom kingdom;

    public Resource() {
    }

    public Resource(String type) {
        this.type = type;
        this.amount = 1;
    }

    public Resource(String type, Kingdom kingdom) {
        this.kingdom = kingdom;
        this.type = type;
        this.amount = 1;
    }

    public Resource(String type, Kingdom kingdom, float amount) {
      this(type, kingdom);
        this.amount = amount;
    }

    public void addResource(float amountOfResourceAdded){
        this.amount +=  amountOfResourceAdded;
    }

    public void subtractResource(float amountOfResourceSubstracted){
        float result = amount - amountOfResourceSubstracted;
        if(result > 0){
            this.amount = result;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
