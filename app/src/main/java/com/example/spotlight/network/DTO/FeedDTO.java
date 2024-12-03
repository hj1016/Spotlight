package com.example.spotlight.network.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class FeedDTO implements Serializable {

    private Long feedId;
    private String title;
    private String thumbnailImage;
    private String feedImg;
    private List<String> feedImages;
    private String content;
    private Integer scrap;
    private boolean scrapped;
    private Integer hitsUser;
    private Integer hitsRecruiter;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private FeedCategoryDTO category;
    private FeedUserDTO user;
    private FeedExhibitionDTO exhibition;

    @SerializedName("project")
    private FeedProjectDTO project;
    private Set<FeedHashtagDTO> hashtags;

    public FeedDTO() {}

    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getFeedImg() {
        return feedImg;
    }

    public void setFeedImg(String feedImg) {
        this.feedImg = feedImg;
    }

    public List<String> getFeedImages() {
        return feedImages;
    }

    public void setFeedImages(List<String> feedImages) {
        this.feedImages = feedImages;
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

    public boolean isScrapped() {
        return scrapped;
    }

    public void setScrapped(boolean scrapped) {
        this.scrapped = scrapped;
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public FeedCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(FeedCategoryDTO category) {
        this.category = category;
    }

    public FeedUserDTO getUser() {
        return user;
    }

    public void setUser(FeedUserDTO user) {
        this.user = user;
    }

    public FeedExhibitionDTO getExhibition() {
        return exhibition;
    }

    public void setExhibition(FeedExhibitionDTO exhibition) {
        this.exhibition = exhibition;
    }

    public FeedProjectDTO getProject() {
        return project;
    }

    public void setProject(FeedProjectDTO project) {
        this.project = project;
    }

    public Set<FeedHashtagDTO> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<FeedHashtagDTO> hashtags) {
        this.hashtags = hashtags;
    }

    // 내부 클래스들에 Serializable 구현 추가
    public static class FeedCategoryDTO implements Serializable {
        private Long id;
        private String name;
        private Long parentId;

        public FeedCategoryDTO() {}

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }
    }

    public static class FeedUserDTO implements Serializable {
        private Long id;
        private String name;

        public FeedUserDTO() {}

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class FeedExhibitionDTO implements Serializable {
        private Long exhibitionId;
        private String location;
        private String schedule;
        private String time;
        private Long userId;
        private Long feedId;

        public FeedExhibitionDTO() {}

        public Long getExhibitionId() {
            return exhibitionId;
        }

        public void setExhibitionId(Long exhibitionId) {
            this.exhibitionId = exhibitionId;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getSchedule() {
            return schedule;
        }

        public void setSchedule(String schedule) {
            this.schedule = schedule;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getFeedId() {
            return feedId;
        }

        public void setFeedId(Long feedId) {
            this.feedId = feedId;
        }
    }

    public static class FeedProjectDTO implements Serializable {
        private Long id;
        private String name;
        private List<ProjectRoleDTO> projectRoles;

        public FeedProjectDTO() {}

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ProjectRoleDTO> getProjectRoles() {
            return projectRoles;
        }

        public void setProjectRoles(List<ProjectRoleDTO> projectRoles) {
            this.projectRoles = projectRoles;
        }

        public static class ProjectRoleDTO implements Serializable {
            private Long id;
            private Long userId;
            private String role;

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

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }
        }
    }

    public static class FeedHashtagDTO implements Serializable {
        private Long id;
        private String hashtag;

        public FeedHashtagDTO() {}

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getHashtag() {
            return hashtag;
        }

        public void setHashtag(String hashtag) {
            this.hashtag = hashtag;
        }
    }
}