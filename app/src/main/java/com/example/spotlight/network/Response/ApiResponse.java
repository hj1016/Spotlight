package com.example.spotlight.network.Response;

import java.io.Serializable;

public class ApiResponse implements Serializable {
    private boolean success;
    private String message;
    private String error;
    private String proposalId;

    public ApiResponse(boolean success, String message, String proposalId) {
        this.success = success;
        this.message = message;
        this.proposalId = proposalId;
    }

    // 게터와 세터
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }
}
