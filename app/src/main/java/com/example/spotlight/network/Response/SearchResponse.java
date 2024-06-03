package com.example.spotlight.network.Response;

import com.example.spotlight.network.DTO.PostDTO;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("posts")
    private List<PostDTO> posts;

    // Getters

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }
}
