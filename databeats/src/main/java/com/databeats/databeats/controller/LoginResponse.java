package com.databeats.databeats.controller;

public class LoginResponse {
    private String role;
    private long id;

    public LoginResponse(String role, long id) {
        this.role = role;
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
