package com.example.spotlight.network.Response;

import com.example.spotlight.network.DTO.ProposalDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ProposalResponse {

    private Long proposalId;
    private String job;
    private String contact;
    private String description;
    private String createdDate;
    private String status;
    private ProposalDTO.ProposalRecruiterDTO recruiter;
    private ProposalDTO.ProposalStudentDTO student;

    // 기본 생성자
    public ProposalResponse() {
    }

    // Getter and Setter
    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProposalDTO.ProposalRecruiterDTO getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(ProposalDTO.ProposalRecruiterDTO recruiter) {
        this.recruiter = recruiter;
    }

    public ProposalDTO.ProposalStudentDTO getStudent() {
        return student;
    }

    public void setStudent(ProposalDTO.ProposalStudentDTO student) {
        this.student = student;
    }

    public String getFormattedDate() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
            LocalDateTime createdDateTime = LocalDateTime.parse(createdDate, formatter);

            LocalDateTime now = LocalDateTime.now();
            long days = ChronoUnit.DAYS.between(createdDateTime, now);
            long hours = ChronoUnit.HOURS.between(createdDateTime, now);

            if (days == 0) {
                if (hours == 0) {
                    return "Just now";
                } else {
                    return hours + " hours ago";
                }
            } else if (days == 1) {
                return "1 day ago";
            } else {
                return days + " days ago";
            }
        } catch (Exception e) {
            return createdDate;
        }
    }
}