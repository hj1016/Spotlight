package com.example.spotlight.network.Request;

public class ExhibitionRequest {

    private String location;
    private String schedule;
    private String time;
    private Long userId;
    private Long feedId;

    // 기본 생성자
    public ExhibitionRequest() {}

    // Getter와 Setter
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }
}