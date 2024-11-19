package com.example.spotlight.network.Request;

import java.util.List;
import java.util.Set;

public class FeedRequest {

    private String projectImage;
    private String title;
    private Long mainCategoryId;
    private Long subCategoryId;
    private List<String> additionalImages;
    private String description;
    private Set<String> hashtags;
    private List<Long> teamMemberIds;
    private Long exhibitionId;

    // 기본 생성자
    public FeedRequest() {}

    // Getter와 Setter
    public String getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(String projectImage) {
        this.projectImage = projectImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(Long mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public List<String> getAdditionalImages() {
        return additionalImages;
    }

    public void setAdditionalImages(List<String> additionalImages) {
        this.additionalImages = additionalImages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<String> hashtags) {
        this.hashtags = hashtags;
    }

    public List<Long> getTeamMemberIds() {
        return teamMemberIds;
    }

    public void setTeamMemberIds(List<Long> teamMemberIds) {
        this.teamMemberIds = teamMemberIds;
    }

    public Long getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(Long exhibitionId) {
        this.exhibitionId = exhibitionId;
    }
}