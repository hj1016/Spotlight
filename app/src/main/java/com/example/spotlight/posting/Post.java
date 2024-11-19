package com.example.spotlight.posting;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {
    private int teamImageUrl;
    private String title;
    private String category;
    private int imageUrl;
    private String content;
    private int scrap;
    private boolean isScrapped;

    private int scrapImageUrl;
    private List<String> hashtags;

    // Constructor
    public Post(int teamImageUrl, String title, String category, int imageUrl, String content, int scrap, List<String> hashtags, int scrapImageUrl, boolean isScrapped) {
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
    public int getTeamImageUrl() {
        return teamImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getImageUrl() {
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

    public int getScrapImageUrl() {
        return scrapImageUrl;
    }

    public boolean isScrapped() {
        return isScrapped;
    }
    public void setScrapped(boolean scrapped) {
        isScrapped = scrapped;
    }
}
