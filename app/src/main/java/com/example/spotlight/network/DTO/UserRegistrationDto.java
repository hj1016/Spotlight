package com.example.spotlight.network.DTO;

public class UserRegistrationDto {

    private String email;
    private String id;
    private String password;
    private String name;
    private String role;
    private String school;
    private String major;
    private String company;
    private String certificate;

    // 기본 생성자
    public UserRegistrationDto() {
    }

    // 생성자
    public UserRegistrationDto(String email, String id, String password, String name, String role) {
        this.email = email;
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    // Getter 및 Setter 메서드
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
}
