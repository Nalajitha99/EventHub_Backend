package com.example.EventHub.Models;

public class AuthRequest {

    private String username;
    private String password;
    private String token;
    private String message;
    private String role;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getToken() {
        return this.token;
    }

    public String getMessage() {
        return this.message;
    }

    public String getRole() {
        return this.role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AuthRequest(String username, String password, String token, String message, String role) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.message = message;
        this.role = role;
    }

    public AuthRequest() {
    }
}

