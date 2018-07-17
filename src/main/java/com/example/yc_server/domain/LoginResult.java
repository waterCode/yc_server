package com.example.yc_server.domain;

public class LoginResult extends Result {
    private String role = "";

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
