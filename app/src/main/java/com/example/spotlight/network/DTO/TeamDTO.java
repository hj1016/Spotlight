package com.example.spotlight.network.DTO;

public class TeamDTO {
    private Integer teamId;
    private Integer projectId;
    private Integer studentId;
    private Integer feedId;
    private Integer userId;

    // 기본 생성자
    public TeamDTO() {
    }

    // 모든 필드를 포함한 생성자
    public TeamDTO(Integer teamId, Integer projectId, Integer studentId, Integer feedId, Integer userId) {
        this.teamId = teamId;
        this.projectId = projectId;
        this.studentId = studentId;
        this.feedId = feedId;
        this.userId = userId;
    }

    // Getters and Setters
    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}