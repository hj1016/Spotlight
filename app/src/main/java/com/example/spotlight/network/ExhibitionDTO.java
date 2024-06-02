package com.example.spotlight.network;

import com.google.gson.annotations.SerializedName;

public class ExhibitionDTO {
    @SerializedName("location")
    private String location;

    @SerializedName("schedule")
    private String schedule;

    @SerializedName("time")
    private String time;

    // Getters

    public String getLocation() {
        return location;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getTime() {
        return time;
    }
}