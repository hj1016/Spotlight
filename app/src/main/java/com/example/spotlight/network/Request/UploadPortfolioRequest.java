package com.example.spotlight.network.Request;

import okhttp3.MultipartBody;

public class UploadPortfolioRequest {
    private MultipartBody.Part[] portfolioImages;

    public MultipartBody.Part[] getPortfolioImages() {
        return portfolioImages;
    }

    public void setPortfolioImages(MultipartBody.Part[] portfolioImages) {
        this.portfolioImages = portfolioImages;
    }
}
