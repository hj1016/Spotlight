package com.example.spotlight.network.DTO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class FeedDTO {

    private Long feedId;
    private String title;
    private String thumbnailImage;
    private List<String> feedImages;
    private String content;
    private Integer scrap;
    private Integer hitsUser;
    private Integer hitsRecruiter;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private FeedCategoryDTO category;
    private FeedUserDTO user;
    private FeedExhibitionDTO exhibition;
    private FeedProjectDTO project;
    private Set<FeedHashtagDTO> hashtags;

    // 기본 생성자
    public FeedDTO() {}

    // Getter와 Setter
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

    // 내부 클래스 정의

    public static class FeedCategoryDTO {
        private Long id;   // 카테고리 ID
        private String name; // 카테고리 이름
        private  Long parentId; // 부모 카테고리 ID

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

        public Long getParentId() { return parentId; }

        public void setParentId(Long parentId){ this.parentId = parentId; }
    }

    public static class FeedUserDTO {
        private Long id;   // 사용자 ID
        private String name; // 사용자 이름

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

    public static class FeedExhibitionDTO {
        private Long exhibitionId; // 전시 ID
        private String location;   // 전시 위치
        private String schedule;   // 전시 일정
        private String time;       // 전시 시간
        private Long userId;       // 사용자 ID
        private Long feedId;       // 피드 ID

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

    public static class FeedProjectDTO {
        private Long id;          // 프로젝트 ID
        private String name;      // 프로젝트 이름
        private List<ProjectRoleDTO> projectRoles; // 프로젝트 역할 리스트

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
    }

    public static class FeedHashtagDTO {
        private Long id;       // 해시태그 ID
        private String hashtag; // 해시태그 이름

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