package com.example.spotlight.network;

public class ErrorResponse {
    private boolean success;
    private String error;
    private String message;

    // Constructor, Getters and Setters
    public ErrorResponse(boolean success, String error, String message) {
        this.success = success;
        this.error = error;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}