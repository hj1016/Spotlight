package com.example.spotlight.network.Response;

import java.time.LocalDateTime;

public class ProposalResponse {
    private int proposalId;
    private String company;
    private String job;
    private String phoneNumber;
    private String description;
    private Integer userId;
    private String createdDate;
    private String profileImage;
    private String username;
    private String daysAgo;
    private String projectName;

    // Getters and Setters

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public String getCompany() { return company; }

    public void setCompany(String company) { this.company = company; }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getCreatedDate() { return createdDate; }

    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }

    public String getProfileImage() { return profileImage; }

    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getDaysAgo() { return daysAgo; }

    public void setDaysAgo(String daysAgo) { this.daysAgo = daysAgo; }

    public String getProjectName() { return projectName; }

    public void setProjectName(String projectName) { this.projectName = projectName; }
}