package com.example.spotlight.network.Response;

public class UserProfileResponse {
    private String profileImage;
    private String username;
    private String id;
    private String school;
    private String major;
    private String company;

    // 기본 생성자
    public UserProfileResponse() {
    }

    // 모든 필드를 포함하는 생성자
    public UserProfileResponse(String profileImage, String username, String id, String school, String major, String company) {
        this.profileImage = profileImage;
        this.username = username;
        this.id = id;
        this.school = school;
        this.major = major;
        this.company = company;
    }

    // Getter와 Setter
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
