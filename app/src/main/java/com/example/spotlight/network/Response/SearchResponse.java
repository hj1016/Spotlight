package com.example.spotlight.network.Response;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.spotlight.posting.Post;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse implements Parcelable { // 추후에 수정 필요.
    private boolean success;
    private String message;
    private List<Post> posts;

    protected SearchResponse(Parcel in) {
        success = in.readByte() != 0;
        message = in.readString();
        posts = new ArrayList<>();
        in.readList(posts, Post.class.getClassLoader());
    }

    public static final Creator<SearchResponse> CREATOR = new Creator<SearchResponse>() {
        @Override
        public SearchResponse createFromParcel(Parcel in) {
            return new SearchResponse(in);
        }

        @Override
        public SearchResponse[] newArray(int size) {
            return new SearchResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeString(message);
        dest.writeList(posts);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}