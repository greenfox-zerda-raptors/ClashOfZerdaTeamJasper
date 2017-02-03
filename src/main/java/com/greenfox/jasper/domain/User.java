package com.greenfox.jasper.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "user_table")
@Component
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name")
    private String name;
    
    // TODO do we need this?
    @JsonBackReference
    @OneToOne (mappedBy = "user", cascade = CascadeType.ALL)
    private Kingdom kingdom;

    @Column(name = "points")
    @NotNull
    private int points;

    public User() {
    }

    public User(String kingdomName, String name) {
        this.kingdom = new Kingdom(kingdomName, this);
        this.name = name;
        this.points = 0;
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

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

