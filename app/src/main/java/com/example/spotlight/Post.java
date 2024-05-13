package com.example.spotlight;

public class Post {
    private String teamImageUrl;
    private String title;
    private String category;
    private String imageUrl;
    private String content;
    private int scrap;

    private String scrapImageUrl;
    private String hashtag;



    // Constructor
    public Post(String teamImageUrl, String title, String category, String imageUrl, String content, int scrap, String hashtag, String scrapImageUrl) {
        this.teamImageUrl = teamImageUrl;
        this.title = title;
        this.category = category;
        this.imageUrl = imageUrl;
        this.content = content;
        this.scrap = scrap;
        this.hashtag = hashtag;
        this.scrapImageUrl = scrapImageUrl;
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

    public String getHashtag() {
        return hashtag;
    }

    public String getScrapImageUrl() {
        return scrapImageUrl;
    }
}
