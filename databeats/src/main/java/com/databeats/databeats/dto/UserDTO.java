package com.databeats.databeats.dto;

public class UserDTO {
    private long userId;
    private String username;
    private String password;
    //add role
    public UserDTO() {}

    public UserDTO(long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
}
