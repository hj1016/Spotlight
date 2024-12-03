package com.example.spotlight.network.DTO;

import com.google.gson.annotations.SerializedName;

public class ProposalDTO {

    @SerializedName("proposalId")
    private Long proposalId;

    @SerializedName("job")
    private String job;

    @SerializedName("contact")
    private String contact;

    @SerializedName("description")
    private String description;

    @SerializedName("createdDate")
    private String createdDate;

    @SerializedName("status")
    private String status;

    @SerializedName("recruiter")
    private ProposalRecruiterDTO recruiter;

    @SerializedName("student")
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

    public static class ProposalRecruiterDTO {

        @SerializedName("userId")
        private Long userId;

        @SerializedName("company")
        private String company;

        @SerializedName("certification")
        private String certification;

        @SerializedName("username")
        private String username;  // 아이디

        @SerializedName("profileImage")
        private String profileImage;

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

    public static class ProposalStudentDTO {

        @SerializedName("userId")
        private Long userId;

        @SerializedName("major")
        private String major;

        @SerializedName("portfolioImage")
        private String portfolioImage;

        @SerializedName("school")
        private String school;

        @SerializedName("profileImage")
        private String profileImage;

        @SerializedName("name")
        private String name;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
