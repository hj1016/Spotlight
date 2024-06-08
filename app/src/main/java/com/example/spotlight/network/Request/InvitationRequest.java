package com.example.spotlight.network.Request;

public class InvitationRequest {
    private String projectId;
    private String memberId;
    private String projectRole;

    public InvitationRequest() {
    }

    public InvitationRequest(String projectId, String memberId, String projectRole) {
        this.projectId = projectId;
        this.memberId = memberId;
        this.projectRole = projectRole;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
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