package com.example.spotlight.network.Response;

import com.example.spotlight.network.data.User;

public class UserResponse {
    private User user;
    private boolean success;
    private String message;

    // 기본 생성자
    public UserResponse() {
    }

    // 생성자
    public UserResponse(User user, boolean success, String message) {
        this.user = user;
        this.success = success;
        this.message = message;
    }

    // getter 및 setter
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

