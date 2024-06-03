package com.example.spotlight.network.Response;

public class FeedHitsResponse {
    private boolean success;
    private int hits_user;
    private int hits_recruiter;
    private String message;

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getHits_user() {
        return hits_user;
    }

    public void setHits_user(int hits_user) {
        this.hits_user = hits_user;
    }

    public int getHits_recruiter() {
        return hits_recruiter;
    }

    public void setHits_recruiter(int hits_recruiter) {
        this.hits_recruiter = hits_recruiter;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}