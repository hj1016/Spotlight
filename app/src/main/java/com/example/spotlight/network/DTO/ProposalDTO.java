package com.example.spotlight.network.DTO;

import java.time.LocalDateTime;

public class ProposalDTO {

    private Long proposalId;
    private String job;
    private String contact;
    private String description;
    private LocalDateTime createdDate;
    private String status;
    private ProposalRecruiterDTO recruiter;
    private ProposalStudentDTO student;

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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProposalRecruiterDTO getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(ProposalRecruiterDTO recruiter) {
        this.recruiter = recruiter;
    }

    public ProposalStudentDTO getStudent() {
        return student;
    }

    public void setStudent(ProposalStudentDTO student) {
        this.student = student;
    }

    // 리크루터 정보 내부 클래스
    public static class ProposalRecruiterDTO {
        private Long userId;       // 리크루터 ID
        private String company;    // 회사 이름
        private String certification; // 인증 정보
        private String username;   // 사용자 이름
        private String profileImage;

        // 기본 생성자
        public ProposalRecruiterDTO(RecruiterDTO recruiter) {
            this.userId = recruiter.getUserId();
            this.company = recruiter.getCompany();
            this.certification = recruiter.getRecruiterCertificate();
            this.username = recruiter.getUser() != null ? recruiter.getUser().getUsername() : null;
            this.profileImage = recruiter.getUser().getProfileImage();
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCertification() {
            return certification;
        }

        public void setCertification(String certification) {
            this.certification = certification;
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
    }

    // 학생 정보 내부 클래스
    public static class ProposalStudentDTO {
        private Long userId;          // 학생 ID
        private String major;         // 전공
        private String portfolioImage; // 포트폴리오 이미지 URL
        private String school;        // 학교 이름
        private String profileImage;

        // 기본 생성자
        public ProposalStudentDTO(StudentDTO student) {
            this.userId = student.getUserId();
            this.major = student.getMajor();
            this.portfolioImage = student.getPortfolioImage();
            this.school = student.getSchool();
            this.profileImage = student.getUser().getProfileImage();
        }
        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getPortfolioImage() {
            return portfolioImage;
        }

        public void setPortfolioImage(String portfolioImage) {
            this.portfolioImage = portfolioImage;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }
    }
}