package com.example.spotlight.network.DTO;

public class SchoolMajorDTO {

    private String schoolName;
    private String majorName;
    private String majorSeq;

    // 기본 생성자
    public SchoolMajorDTO() {}

    // 생성자 (학교 이름, 학과 이름)
    public SchoolMajorDTO(String schoolName, String majorName) {
        this.schoolName = schoolName;
        this.majorName = majorName;
    }

    // 생성자 (학교 이름, 학과 이름, 학과 코드)
    public SchoolMajorDTO(String schoolName, String majorName, String majorSeq) {
        this.schoolName = schoolName;
        this.majorName = majorName;
        this.majorSeq = majorSeq;
    }

    // Getter와 Setter
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorSeq() {
        return majorSeq;
    }

    public void setMajorSeq(String majorSeq) {
        this.majorSeq = majorSeq;
    }

    @Override
    public String toString() {
        return "SchoolMajorDTO{" +
                "schoolName='" + schoolName + '\'' +
                ", majorName='" + majorName + '\'' +
                ", majorSeq='" + majorSeq + '\'' +
                '}';
    }
}