package com.intv.test.security;

public class UserAuth {
    private String userId;
    private String role; // USER or ADMIN

    public boolean isAdmin() { return "ADMIN".equals(role); }
    public boolean isUser() { return "USER".equals(role); }

    public UserAuth(){}

    public UserAuth(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}