package com.pk.messenger.dto;

import com.pk.messenger.enums.UserRole;

public class MyUserRequestDTO {
    private UserRole role;
    private String username;

    public String getUsername() {
        return username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
