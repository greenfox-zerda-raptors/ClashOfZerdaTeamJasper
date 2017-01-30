package com.greenfox.jasper.domain;

/**
 * Created by almasics on 2017.01.30..
 */
public class AccountInfo {

    private String href;
    private String fullName;
    private String email;

    public AccountInfo(String email, String fullName, String href) {
        this.email = email;
        this.fullName = fullName;
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

}