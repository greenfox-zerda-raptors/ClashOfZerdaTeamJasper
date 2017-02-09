package com.greenfox.jasper.dto;

// TODO discuss!!!
public class PostDto {
    private long id;
    private String type;

    public PostDto() {
    }

    public PostDto(long id, String type) {
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
