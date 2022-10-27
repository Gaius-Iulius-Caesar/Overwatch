package com.overwatch.dataServer.entity;

import com.alibaba.fastjson.JSON;

import java.util.Objects;

public class User {
    private int Id;
    private String username;
    private String password;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return JSON.toJSONString(this) + "\n";
    }
}
