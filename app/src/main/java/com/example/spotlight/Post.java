package com.example.spotlight;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {
    private String teamImageUrl;
    private String title;
    private String category;
    private String imageUrl;
    private String content;
    private int scrap;
    private boolean isScrapped;

    private String scrapImageUrl;
    private List<String> hashtags;

    // Constructor
    public Post(String teamImageUrl, String title, String category, String imageUrl, String content, int scrap, List<String> hashtags, String scrapImageUrl, boolean isScrapped) {
        this.teamImageUrl = teamImageUrl;
        this.title = title;
        this.category = category;
        this.imageUrl = imageUrl;
        this.content = content;
        this.scrap = scrap;
        this.scrapImageUrl = scrapImageUrl;
        this.hashtags = hashtags;
        this.isScrapped = isScrapped;
    }

    // Getters
    public String getTeamImageUrl() {
        return teamImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getContent() {
        return content;
    }

    public int getScrap() {
        return scrap;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public String getScrapImageUrl() {
        return scrapImageUrl;
    }

    public boolean isScrapped() {
        return isScrapped;
    }
    public void setScrapped(boolean scrapped) {
        isScrapped = scrapped;
    }
}
