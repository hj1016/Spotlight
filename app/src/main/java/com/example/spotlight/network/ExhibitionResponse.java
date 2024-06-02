package com.example.spotlight.network;

import com.google.gson.annotations.SerializedName;
import java.util.Optional;

public class ExhibitionResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("exhibition")
    private Optional<FeedRequest.Exhibition> exhibition;

    public ExhibitionResponse(boolean success, Optional<FeedRequest.Exhibition> exhibition) {
        this.success = success;
        this.exhibition = exhibition;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) { this.success = success; }

    public Optional<FeedRequest.Exhibition> getExhibition() {
        return exhibition;
    }

    public void setExhibition(Optional<FeedRequest.Exhibition> exhibition) { this.exhibition = exhibition; }
}