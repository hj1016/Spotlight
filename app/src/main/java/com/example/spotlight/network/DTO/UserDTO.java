package com.example.spotlight.network.DTO;


public class UserDTO {
    private String email;
    private String id;
    private String name;
    private String role;
    private int userId;
    private String profileImage;
    private String school;
    private String major;
    private String company;
    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    // Constructor
    public UserDTO(String email, String id, int userId, String name, String profileImage, String school, String major, String company, String role) {
        this.email = email;
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.profileImage = profileImage;
        this.school = school;
        this.major = major;
        this.company = company;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
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
