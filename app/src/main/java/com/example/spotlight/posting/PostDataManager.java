package com.example.spotlight.posting;

import android.content.Context;

import com.example.spotlight.network.Util.TokenManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PostDataManager {

    private static final String POST_KEY = "PostKey"; // 저장에 사용할 키
    private Gson gson;

    public PostDataManager(Context context) {
        // TokenManager 초기화
        TokenManager.init(context);
        this.gson = new Gson();
    }

    // 포스트 저장
    public void savePosts(List<Post> posts) {
        String json = gson.toJson(posts); // Post 리스트를 JSON 문자열로 변환
        TokenManager.setTokenForKey(POST_KEY, json); // TokenManager를 통해 저장
    }

    // 저장된 포스트 불러오기
    public List<Post> loadPosts() {
        String json = TokenManager.getTokenForKey(POST_KEY); // TokenManager에서 JSON 문자열 가져오기
        if (json == null) return null; // 데이터가 없으면 null 반환
        Type type = new TypeToken<List<Post>>() {}.getType();
        return gson.fromJson(json, type); // JSON 문자열을 Post 리스트로 변환
    }

    // 데이터 삭제
    public void clearPosts() {
        TokenManager.clearTokenForKey(POST_KEY); // TokenManager에서 해당 키 삭제
    }
}