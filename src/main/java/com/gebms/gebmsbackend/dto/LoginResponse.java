package com.gebms.gebmsbackend.dto;

public class LoginResponse {
    private String token;
    private String message;
    private int status;

    // Constructor
    public LoginResponse(String token, String message, int status) {
        this.token = token;
        this.message = message;
        this.status = status;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}