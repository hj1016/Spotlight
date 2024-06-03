package com.example.spotlight.network.DTO;

public class ScrapDTO {
    // Getters and Setters
    private Integer userId;
    private String username;
    private String profileImage;
    private String projectRole;

    public ScrapDTO(Integer userId, String username, String profileImage, String projectRole) {
        this.userId = userId;
        this.username = username;
        this.profileImage = profileImage;
        this.projectRole = projectRole;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }
}
