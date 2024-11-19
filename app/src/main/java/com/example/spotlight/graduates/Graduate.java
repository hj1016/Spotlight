package com.example.spotlight.graduates;

public class Graduate {
    private String photoUrl;
    private String name;
    private String projectName;

    public Graduate(String photoUrl, String name, String projectName) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.projectName = projectName;
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
}
