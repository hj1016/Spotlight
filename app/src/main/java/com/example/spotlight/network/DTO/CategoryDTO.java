package com.example.spotlight.network.DTO;

import com.google.gson.annotations.SerializedName;

public class CategoryDTO {
    @SerializedName("main")
    private String main;

    @SerializedName("sub")
    private String sub;

    // Getters

    public String getMain() {
        return main;
    }

    public String getSub() {
        return sub;
    }
}