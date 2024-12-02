package com.example.spotlight.graduates;

public class Graduate {
    private String name;
    private String feedTitle;
    private String profileImage;

    public Graduate() {}

    public Graduate(String name, String feedTitle, String profileImage) {
        this.name = name;
        this.feedTitle = feedTitle;
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
