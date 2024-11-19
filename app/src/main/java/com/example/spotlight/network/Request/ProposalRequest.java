package com.example.spotlight.network.Request;

public class ProposalRequest {

    private String job;
    private String contact;
    private String description;
    private Long recruiterId;
    private Long studentId;

    // 기본 생성자
    public ProposalRequest() {}

    // 필드 초기화 생성자
    public ProposalRequest(String job, String contact, String description, Long recruiterId, Long studentId) {
        this.job = job;
        this.contact = contact;
        this.description = description;
        this.recruiterId = recruiterId;
        this.studentId = studentId;
    }

    // Getter와 Setter
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}