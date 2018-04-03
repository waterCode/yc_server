package com.example.yc_server.domain;

public class LoginResult extends Result {
    private String token="";


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
