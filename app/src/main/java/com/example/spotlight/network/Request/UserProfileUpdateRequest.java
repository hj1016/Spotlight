package com.example.spotlight.network.Request;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserProfileUpdateRequest {
    private RequestBody username;
    private MultipartBody.Part profileImage;

    public RequestBody getUsername() {
        return username;
    }

    public void setUsername(RequestBody username) {
        this.username = username;
    }

    public MultipartBody.Part getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(MultipartBody.Part profileImage) {
        this.profileImage = profileImage;
    }
}
