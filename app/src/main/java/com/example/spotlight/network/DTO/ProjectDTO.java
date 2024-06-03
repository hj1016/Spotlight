package com.example.spotlight.network.DTO;

public class ProjectDTO {
    private Integer projectId;
    private String projectName;

    // Constructors
    public ProjectDTO() {
    }

    public ProjectDTO(Integer projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }

    // Getters and Setters
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}