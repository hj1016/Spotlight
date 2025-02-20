package com.example.spotlight.posting;

import com.example.spotlight.network.DTO.FeedDTO;
import com.example.spotlight.network.DTO.FeedToPostConverter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class Post implements Serializable {
    private Long feedId;
    private String thumbnailImage;
    private String title;
    private String content;
    private Integer scrap;
    private Category category;
    private String feedImg;
    private List<String> feedImages;
    private List<Hashtag> hashtags;
    private boolean scrapped;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private User user;
    private Exhibition exhibition;

    // 기본 생성자
    public Post() {}

    // 생성자
    public Post(Long feedId, String thumbnailImage, String title, String content,
                Integer scrap, Category category, String feedImg, List<String> feedImages,
                List<Hashtag> hashtags, boolean scrapped, Timestamp createdDate, Timestamp modifiedDate,
                User user, Exhibition exhibition) {
        this.feedId = feedId;
        this.thumbnailImage = thumbnailImage;
        this.title = title;
        this.content = content;
        this.scrap = scrap;
        this.category = category;
        this.feedImg = feedImg;
        this.feedImages = feedImages;
        this.hashtags = hashtags;
        this.scrapped = scrapped;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.user = user;
        this.exhibition = exhibition;
    }

    // Getters and Setters
    public Long getFeedId() { return feedId; }
    public String getThumbnailImage() { return thumbnailImage; }
    public String getTitle() { return title; }
    public String getContent() { return content; }

    public Integer getScrap() { return scrap; }

    public Category getCategory() { return category; }

    public String getFeedImg() {
        return feedImg;
    }

    public void setFeedImg(String feedImg) {
        this.feedImg = feedImg;
    }

    public List<String> getFeedImages() { return feedImages; }
    public List<Hashtag> getHashtags() { return hashtags; }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public User getUser() { return user; }
    public Exhibition getExhibition() { return exhibition; }

    public void setFeedId(Long feedId) { this.feedId = feedId; }
    public void setThumbnailImage(String thumbnailImage) { this.thumbnailImage = thumbnailImage; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }

    public void setScrap(Integer scrap) { this.scrap = scrap; }

    public void setCategory(Category category) { this.category = category; }

    public void setFeedImages(List<String> feedImages) { this.feedImages = feedImages; }
    public void setHashtags(List<Hashtag> hashtags) { this.hashtags = hashtags; }

    public boolean isScrapped() { return scrapped; }

    public void setScrapped(boolean scrapped) { this.scrapped = scrapped; }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setUser(User user) { this.user = user; }
    public void setExhibition(Exhibition exhibition) { this.exhibition = exhibition; }

    public static class User {
        private Long id;
        private String name;

        public User(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() { return id; }
        public String getName() { return name; }
        public void setId(Long id) { this.id = id; }
        public void setName(String name) { this.name = name; }
    }

    public static class Category {
        private Long id;
        private String name;
        private Long parentId;

        public Category(Long id, String name, Long parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
        }

        public Long getId() { return id; }
        public String getName() { return name; }
        public Long getParentId() { return parentId; }

        public void setId(Long id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public void setParentId(Long parentId) { this.parentId = parentId; }
    }

    public static class Hashtag {
        private Long id;
        private String hashtag;

        public Hashtag(Long id, String hashtag) {
            this.id = id;
            this.hashtag = hashtag;
        }

        public Long getId() { return id; }
        public String getHashtag() { return hashtag; }
        public void setId(Long id) { this.id = id; }
        public void setHashtag(String hashtag) { this.hashtag = hashtag; }
    }

    public static class Exhibition {
        private Long exhibitionId;
        private String location;
        private String schedule;
        private String time;
        private Long userId;
        private Long feedId;

        public Exhibition(Long exhibitionId, String location, String schedule, String time, Long userId, Long feedId) {
            this.exhibitionId = exhibitionId;
            this.location = location;
            this.schedule = schedule;
            this.time = time;
            this.userId = userId;
            this.feedId = feedId;
        }

        public Long getExhibitionId() { return exhibitionId; }
        public String getLocation() { return location; }
        public String getSchedule() { return schedule; }
        public String getTime() { return time; }
        public Long getUserId() { return userId; }
        public Long getFeedId() { return feedId; }

        public void setExhibitionId(Long exhibitionId) { this.exhibitionId = exhibitionId; }
        public void setLocation(String location) { this.location = location; }
        public void setSchedule(String schedule) { this.schedule = schedule; }
        public void setTime(String time) { this.time = time; }
        public void setUserId(Long userId) { this.userId = userId; }
        public void setFeedId(Long feedId) { this.feedId = feedId; }
    }
}