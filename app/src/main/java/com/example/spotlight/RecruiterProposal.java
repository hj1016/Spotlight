package com.example.spotlight;

public class RecruiterProposal {
    private String photoUrl;
    private String name;
    private String projectName;
    private String companyName;
    private String proposeDate;
    private String role;

    public RecruiterProposal(String photoUrl, String name, String projectName, String companyName, String proposeDate, String role) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.projectName = projectName;
        this.companyName = companyName;
        this.proposeDate = proposeDate;
        this.role = role;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getName() {
        return name;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getProposeDate() {
        return proposeDate;
    }

    public String getRole() {
        return role;
    }
}
