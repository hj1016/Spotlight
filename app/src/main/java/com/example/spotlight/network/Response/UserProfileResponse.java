package com.example.spotlight.network.Response;

public class UserProfileResponse {
    private boolean success;
    private String name;
    private String username;
    private String password;
    private String profileImageUrl;
    private String school;
    private String major;
    private String company;

    public UserProfileResponse() {}

    public UserProfileResponse(String name, String username, String password, String profileImageUrl, String school, String major, String company) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.school = school;
        this.major = major;
        this.company = company;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "UserProfileResponse{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                '}';
    }
}
