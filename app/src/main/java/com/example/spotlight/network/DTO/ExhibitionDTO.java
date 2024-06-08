package com.example.spotlight.network.DTO;

import com.google.gson.annotations.SerializedName;

public class ExhibitionDTO {

    @SerializedName("location")
    private String location;

    @SerializedName("schedule")
    private String schedule;

    @SerializedName("time")
    private String time;

    @SerializedName("userId")
    private Integer userId;

    public ExhibitionDTO(String location, String schedule, String time, Integer userId) {
        this.location = location;
        this.schedule = schedule;
        this.time = time;
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) { this.location = location; }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) { this.schedule = schedule; }

    public String getTime() {
        return time;
    }

    public void setTime(String time) { this.time = time; }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}