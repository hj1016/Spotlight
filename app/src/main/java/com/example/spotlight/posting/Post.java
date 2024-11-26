package com.example.spotlight.posting;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {
    private Long feedId;              // 피드 ID
    private String thumbnailImage;    // 썸네일 이미지 URL
    private String title;             // 제목
    private String category;          // 카테고리
    private List<String> feedImages;  // 추가 이미지 URL 리스트
    private String content;           // 내용
    private int scrapCount;           // 스크랩 수
    private boolean scrapped;         // 스크랩 여부
    private List<String> hashtags;    // 해시태그 리스트

    // 기본 생성자
    public Post() {}

    // 생성자
    public Post(Long feedId, String thumbnailImage, String title, String category,
                List<String> feedImages, String content, int scrapCount,
                boolean scrapped, List<String> hashtags) {
        this.feedId = feedId;
        this.thumbnailImage = thumbnailImage;
        this.title = title;
        this.category = category;
        this.feedImages = feedImages;
        this.content = content;
        this.scrapCount = scrapCount;
        this.scrapped = scrapped;
        this.hashtags = hashtags;
    }

    // Getters
    public Long getFeedId() {
        return feedId;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getFeedImages() {
        return feedImages;
    }

    public String getContent() {
        return content;
    }

    public int getScrapCount() {
        return scrapCount;
    }

    public boolean isScrapped() {
        return scrapped;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    // Setters
    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFeedImages(List<String> feedImages) {
        this.feedImages = feedImages;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setScrapCount(int scrapCount) {
        this.scrapCount = scrapCount;
    }

    public void setScrapped(boolean scrapped) {
        this.scrapped = scrapped;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }
}