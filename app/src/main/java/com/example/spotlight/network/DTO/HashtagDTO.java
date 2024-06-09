package com.example.spotlight.network.DTO;

public class HashtagDTO {
    private Integer id;
    private String hashtag;
    private String createdDate;

    // Constructors
    public HashtagDTO() {
    }

    public HashtagDTO(Integer id, String hashtag, String createdDate) {
        this.id = id;
        this.hashtag = hashtag;
        this.createdDate = createdDate;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}