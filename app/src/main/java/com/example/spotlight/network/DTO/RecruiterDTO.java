package com.example.spotlight.network.DTO;

public class RecruiterDTO {

    private Long userId;          // 사용자 ID
    private String company;       // 회사 이름
    private String recruiterCertificate; // 재직 증명서 URL
    private UserDTO user;       // 사용자 정보

    // 기본 생성자
    public RecruiterDTO() {}

    // Getter와 Setter
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

    public String getRecruiterCertificate() {
        return recruiterCertificate;
    }

    public void setRecruiterCertificate(String recruiterCertificate) {
        this.recruiterCertificate = recruiterCertificate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    // 내부 클래스: RecruiterUserDTO
    public static class RecruiterUserDTO {
        private Long id;        // 사용자 ID
        private String username; // 사용자 이름
        private String email;    // 이메일 주소
        private String name;     // 사용자 실명

        public RecruiterUserDTO() {}

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
}