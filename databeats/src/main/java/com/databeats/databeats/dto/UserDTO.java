package com.databeats.databeats.dto;

import com.databeats.databeats.model.Roles;

public class UserDTO {
    private long userId;
    private String username;
    private String password;
    private Roles role = Roles.USER;

    public UserDTO() {
    }

    public UserDTO(long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public UserDTO(long userId, String username, String password, Roles role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO(long userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = Enum.valueOf(Roles.class, role);
    }

    public long getUserId() {
        return userId;
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

    public String getRole() {
        return role.toString();
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
