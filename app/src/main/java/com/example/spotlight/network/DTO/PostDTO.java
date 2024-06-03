package com.example.spotlight.network.DTO;

import com.example.spotlight.network.DTO.CategoryDTO;
import com.example.spotlight.network.DTO.ExhibitionDTO;
import com.example.spotlight.network.DTO.MemberDTO;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PostDTO {
    @SerializedName("post_id")
    private String postId;

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("content")
    private String content;

    @SerializedName("scrap")
    private int scrap;

    @SerializedName("member")
    private MemberDTO member;

    @SerializedName("exhibition")
    private ExhibitionDTO exhibition;

    @SerializedName("hits_user")
    private int hitsUser;

    @SerializedName("hits_recruiter")
    private int hitsRecruiter;

    @SerializedName("category")
    private CategoryDTO category;

    @SerializedName("hashtag")
    private List<String> hashtag;

    // Getters

    public String getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public int getScrap() {
        return scrap;
    }

    public MemberDTO getMember() {
        return member;
    }

    public ExhibitionDTO getExhibition() {
        return exhibition;
    }

    public int getHitsUser() {
        return hitsUser;
    }

    public int getHitsRecruiter() {
        return hitsRecruiter;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public List<String> getHashtag() {
        return hashtag;
    }
}