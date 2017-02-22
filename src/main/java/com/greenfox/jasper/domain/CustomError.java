package com.greenfox.jasper.domain;

public class CustomError {

    private String message;
    private long httpStatusCode;

    public CustomError() {
    }

    public CustomError(String message, long httpStatusCode) {
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(long httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
