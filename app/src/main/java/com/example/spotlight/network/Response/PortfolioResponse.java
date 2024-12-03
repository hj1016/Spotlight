package com.example.spotlight.network.Response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PortfolioResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("portfolioList")
    private List<String> portfolioList;

    // Getter and Setter
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

    @Override
    public String toString() {
        return "PortfolioResponse{" +
                "portfolioList=" + portfolioList +
                '}';
    }
}