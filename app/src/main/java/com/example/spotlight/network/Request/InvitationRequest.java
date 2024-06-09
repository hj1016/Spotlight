package com.example.spotlight.network.Request;

public class InvitationRequest {
    private int projectId;
    private String memberId;
    private String projectRole;

    public InvitationRequest() {
    }

    public InvitationRequest(int projectId, String memberId, String projectRole) {
        this.projectId = projectId;
        this.memberId = memberId;
        this.projectRole = projectRole;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }
}