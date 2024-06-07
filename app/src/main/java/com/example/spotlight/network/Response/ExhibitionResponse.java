package com.example.spotlight.network.Response;

import com.example.spotlight.network.DTO.ExhibitionDTO;
import com.google.gson.annotations.SerializedName;

public class ExhibitionResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("exhibition")
    private ExhibitionDTO exhibition;

    public ExhibitionResponse(boolean success, ExhibitionDTO exhibition) {
        this.success = success;
        this.exhibition = exhibition;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ExhibitionDTO getExhibition() {
        return exhibition;
    }

    public void setExhibition(ExhibitionDTO exhibition) {
        this.exhibition = exhibition;
    }
}