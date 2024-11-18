package com.example.spotlight.network.DTO;

public class ProjectRoleDTO {

    private Long id;
    private Long userId;
    private Long projectId;
    private String role;
    private boolean accepted;

    // 기본 생성자
    public ProjectRoleDTO() {}

    // 필드 초기화 생성자
    public ProjectRoleDTO(Long id, Long userId, Long projectId, String role, boolean accepted) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
        this.role = role;
        this.accepted = accepted;
    }

    // Getter와 Setter
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