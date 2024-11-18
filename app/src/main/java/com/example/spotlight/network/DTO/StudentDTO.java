package com.example.spotlight.network.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class StudentDTO {

    private Long userId;          // 사용자 ID
    private String school;        // 학교 이름
    private String major;         // 전공
    private String portfolioImage; // 포트폴리오 이미지 URL
    private String studentCertificate; // 재학 증명서 URL
    private StudentUserDTO user;      // 사용자 정보
    private List<StudentProposalDTO> proposals; // 제안서 목록
    private Set<StudentProjectRoleDTO> projectRoles; // 프로젝트 역할 목록

    // 기본 생성자
    public StudentDTO() {
    }

    // Getter와 Setter
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPortfolioImage() {
        return portfolioImage;
    }

    public void setPortfolioImage(String portfolioImage) {
        this.portfolioImage = portfolioImage;
    }

    public String getStudentCertificate() {
        return studentCertificate;
    }

    public void setStudentCertificate(String studentCertificate) {
        this.studentCertificate = studentCertificate;
    }

    public StudentUserDTO getUser() {
        return user;
    }

    public void setUser(StudentUserDTO user) {
        this.user = user;
    }

    public List<StudentProposalDTO> getProposals() {
        return proposals;
    }

    public void setProposals(List<StudentProposalDTO> proposals) {
        this.proposals = proposals;
    }

    public Set<StudentProjectRoleDTO> getProjectRoles() {
        return projectRoles;
    }

    public void setProjectRoles(Set<StudentProjectRoleDTO> projectRoles) {
        this.projectRoles = projectRoles;
    }

    // 내부 클래스: StudentUserDTO
    public static class StudentUserDTO {
        private Long id;        // 사용자 ID
        private String username; // 사용자 이름
        private String email;    // 이메일 주소
        private String name;     // 사용자 실명

        public StudentUserDTO() {
        }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // 내부 클래스: StudentProposalDTO
    public static class StudentProposalDTO {
        private Long proposalId; // 제안서 ID
        private String job;      // 직무
        private String contact;  // 연락처
        private String description; // 제안서 설명
        private LocalDateTime createdDate; // 생성 일자
        private String status;   // 상태
        private RecruiterDTO recruiter; // 리크루터 정보

        public StudentProposalDTO() {
        }

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

        public RecruiterDTO getRecruiter() {
            return recruiter;
        }

        public void setRecruiter(RecruiterDTO recruiter) {
            this.recruiter = recruiter;
        }
    }

    // 내부 클래스: StudentProjectRoleDTO
    public static class StudentProjectRoleDTO {
        private Long id;       // 역할 ID
        private Long userId;   // 사용자 ID
        private Long projectId; // 프로젝트 ID
        private String role;   // 역할
        private boolean accepted; // 초대 수락 여부

        public StudentProjectRoleDTO() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getProjectId() {
            return projectId;
        }

        public void setProjectId(Long projectId) {
            this.projectId = projectId;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public boolean isAccepted() {
            return accepted;
        }

        public void setAccepted(boolean accepted) {
            this.accepted = accepted;
        }
    }
}