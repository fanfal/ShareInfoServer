package com.au.shareinfoserver.security.model;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = -8445943548965154778L;

    private String phoneNum;
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String phoneNum, String password) {
        this.phoneNum = phoneNum;
        this.password = password;
    }


    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
