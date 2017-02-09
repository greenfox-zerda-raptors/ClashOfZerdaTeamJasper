package com.greenfox.jasper.domain;


public class BuildingPost {
    private long id;
    private String type;

    public BuildingPost() {
    }

    public BuildingPost(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
