package com.example.spotlight.network.Response;

public class ExistIdResponse {

    private boolean success;

    public ExistIdResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
