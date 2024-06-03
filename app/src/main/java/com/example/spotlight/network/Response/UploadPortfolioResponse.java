package com.example.spotlight.network.Response;

import java.util.List;

public class UploadPortfolioResponse {
    private boolean success;
    private String message;
    private List<String> portfolioList;

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

    public List<String> getPortfolioList() {
        return portfolioList;
    }

    public void setPortfolioList(List<String> portfolioList) {
        this.portfolioList = portfolioList;
    }
}
