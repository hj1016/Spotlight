package com.example.spotlight.network.Response;

import java.util.List;

public class PortfolioResponse {
    private boolean success;
    private String message;
    private List<String> portfolioImages;

    // 기본 생성자
    public PortfolioResponse() {
    }

    // 모든 필드를 포함하는 생성자
    public PortfolioResponse(boolean success, String message, List<String> portfolioImages) {
        this.success = success;
        this.message = message;
        this.portfolioImages = portfolioImages;
    }

    // Getter와 Setter
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

    public List<String> getPortfolioImages() {
        return portfolioImages;
    }

    public void setPortfolioImages(List<String> portfolioImages) {
        this.portfolioImages = portfolioImages;
    }
}
