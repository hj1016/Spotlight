package com.example.spotlight.network.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private Boolean emailVerified;
    private String name;
    private String profileImage;
    private String role;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private UserStudentDTO student;
    private UserRecruiterDTO recruiter;
    private List<UserHashtagDTO> hashtags;
    private List<UserFeedDTO> feeds;
    private List<UserScrapDTO> scraps;

    // 기본 생성자
    public UserDTO() {}

    // Getter와 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UserStudentDTO getStudent() {
        return student;
    }

    public void setStudent(UserStudentDTO student) {
        this.student = student;
    }

    public UserRecruiterDTO getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(UserRecruiterDTO recruiter) {
        this.recruiter = recruiter;
    }

    public List<UserHashtagDTO> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<UserHashtagDTO> hashtags) {
        this.hashtags = hashtags;
    }

    public List<UserFeedDTO> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<UserFeedDTO> feeds) {
        this.feeds = feeds;
    }

    public List<UserScrapDTO> getScraps() {
        return scraps;
    }

    public void setScraps(List<UserScrapDTO> scraps) {
        this.scraps = scraps;
    }

    // 내부 클래스 정의

    public static class UserStudentDTO {
        private String school; // 학교
        private String major;  // 전공

        public UserStudentDTO() {}

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
    }

    public static class UserRecruiterDTO {
        private String company;       // 회사
        private String certification; // 재직 증명서 URL

        public UserRecruiterDTO() {}

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
    }

    public static class UserHashtagDTO {
        private Long id;       // 해시태그 ID
        private String hashtag; // 해시태그 이름

        public UserHashtagDTO() {}

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getHashtag() {
            return hashtag;
        }

        public void setHashtag(String hashtag) {
            this.hashtag = hashtag;
        }
    }

    public static class UserFeedDTO {
        private Long feedId; // 피드 ID
        private String title; // 피드 제목

        public UserFeedDTO() {}

        public Long getFeedId() {
            return feedId;
        }

        public void setFeedId(Long feedId) {
            this.feedId = feedId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class UserScrapDTO {
        private Long scrapId;        // 스크랩 ID
        private String feedTitle;    // 스크랩한 피드 제목
        private Long stageId;        // 단계 ID
        private String scrappedUserName; // 스크랩된 사용자 이름

        public UserScrapDTO() {}

        public Long getScrapId() {
            return scrapId;
        }

        public void setScrapId(Long scrapId) {
            this.scrapId = scrapId;
        }

        public String getFeedTitle() {
            return feedTitle;
        }

        public void setFeedTitle(String feedTitle) {
            this.feedTitle = feedTitle;
        }

        public Long getStageId() {
            return stageId;
        }

        public void setStageId(Long stageId) {
            this.stageId = stageId;
        }

        public String getScrappedUserName() {
            return scrappedUserName;
        }

        public void setScrappedUserName(String scrappedUserName) {
            this.scrappedUserName = scrappedUserName;
        }
    }
}