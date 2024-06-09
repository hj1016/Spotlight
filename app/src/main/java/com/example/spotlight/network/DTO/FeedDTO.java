package com.example.spotlight.network.DTO;

import java.util.List;

public class FeedDTO {
    private Integer userId;
    private Integer feedId;
    private String title;
    private String image;
    private String content;
    private Integer scrap;
    private Integer hitsUser;
    private Integer hitsRecruiter;
    private String category;
    private String mainCategory;
    private String subCategory;
    private List<String> hashtags;
    private UserRegistrationDto userDTO;
    private MemberDTO member;
    private ExhibitionDTO exhibition;
    private Integer projectId;
    private ProjectDTO projectDTO;

    // Constructors
    public FeedDTO() {
    }

    public FeedDTO(Integer userId, Integer feedId, String title, String image, String content, Integer scrap, Integer hitsUser, Integer hitsRecruiter, String category, String mainCategory, String subCategory, List<String> hashtags, UserRegistrationDto userDTO, MemberDTO member, ExhibitionDTO exhibition, Integer projectId, ProjectDTO projectDTO) {
        this.userId = userId;
        this.feedId = feedId;
        this.title = title;
        this.image = image;
        this.content = content;
        this.scrap = scrap;
        this.hitsUser = hitsUser;
        this.hitsRecruiter = hitsRecruiter;
        this.category = category;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.hashtags = hashtags;
        this.userDTO = userDTO;
        this.member = member;
        this.exhibition = exhibition;
        this.projectId = projectId;
        this.projectDTO = projectDTO;
    }

    // Getters and Setters
    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getFeedId() { return feedId; }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScrap() {
        return scrap;
    }

    public void setScrap(Integer scrap) {
        this.scrap = scrap;
    }

    public Integer getHitsUser() {
        return hitsUser;
    }

    public void setHitsUser(Integer hitsUser) {
        this.hitsUser = hitsUser;
    }

    public Integer getHitsRecruiter() {
        return hitsRecruiter;
    }

    public void setHitsRecruiter(Integer hitsRecruiter) {
        this.hitsRecruiter = hitsRecruiter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public UserRegistrationDto getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserRegistrationDto userDTO) {
        this.userDTO = userDTO;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public ExhibitionDTO getExhibition() {
        return exhibition;
    }

    public void setExhibition(ExhibitionDTO exhibition) {
        this.exhibition = exhibition;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }
}