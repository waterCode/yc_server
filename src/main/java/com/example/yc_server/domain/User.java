package com.example.yc_server.domain;

public class User {

    private String name;
    private String pwd;


    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
