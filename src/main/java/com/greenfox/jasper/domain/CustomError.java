package com.greenfox.jasper.domain;

public class CustomError {


    private String message;
    private long id;

    public CustomError() {
    }

    public CustomError(String message, long id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
