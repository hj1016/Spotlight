package com.example.spotlight;

public class GraduatesProposal {
    private String companyName;
    private String role;
    private String proposeDate;
    private String photoUrl;

    // Constructor
    public GraduatesProposal(String companyName, String role, String proposeDate, String photoUrl) {
        this.companyName = companyName;
        this.role = role;
        this.proposeDate = proposeDate;
        this.photoUrl = photoUrl;
    }

    // Getters
    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }

    public String getProposeDate() {
        return proposeDate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
