package com.example.spotlight.network.Request;

public class InvitationRequest {

    private String username;  // 초대받는 사용자 ID
    private Long projectId;   // 초대 대상 프로젝트 ID
    private String role;      // 초대 시 지정된 역할

    // 기본 생성자
    public InvitationRequest() {}

    // 필드 초기화 생성자
    public InvitationRequest(String username, Long projectId, String role) {
        this.username = username;
        this.projectId = projectId;
        this.role = role;
    }

    // Getter와 Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}