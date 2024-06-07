package com.example.spotlight.network.DTO;

import com.google.gson.annotations.SerializedName;

public class MemberDTO {
    @SerializedName("name")
    private String name;

    @SerializedName("role")
    private String role;

    public MemberDTO() {
    }

    public MemberDTO(String name, String role) {
        this.name = name;
        this.role = role;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}