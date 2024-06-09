package com.example.spotlight.network.DTO;

public class SchoolMajorDTO {
    private String schoolName;
    private String schoolId;
    private String majorName;
    private String majorId;

    public SchoolMajorDTO(String schoolName, String majorName) {
        this.schoolName = schoolName;
        this.majorName = majorName;
    }

    // Getters and setters
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }
}