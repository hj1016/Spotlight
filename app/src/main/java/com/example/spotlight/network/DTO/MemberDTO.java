package com.example.spotlight.network.DTO;

import com.google.gson.annotations.SerializedName;

public class MemberDTO {
    @SerializedName("name")
    private String name;

    @SerializedName("role")
    private String role;

    // Getters

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}