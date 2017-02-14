package com.greenfox.jasper.dto;


public class BuildingDto {
    private long id;
    private String type;
    private int level;
    private int hp;

    public String getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "BuildingDto{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", level=" + level +
                ", hp=" + hp +
                '}';
    }
}
