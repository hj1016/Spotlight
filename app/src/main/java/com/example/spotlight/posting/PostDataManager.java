package com.example.spotlight.posting;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.spotlight.posting.Post;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class PostDataManager {
    private static final String POST_PREFS = "PostPrefs";
    private static final String POST_KEY = "PostKey";

    private Context context;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public PostDataManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(POST_PREFS, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // 포스트 저장
    public void savePosts(List<Post> posts) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(posts);
        editor.putString(POST_KEY, json);
        editor.apply();
    }

    // 저장된 포스트 불러오기
    public List<Post> loadPosts() {
        String json = sharedPreferences.getString(POST_KEY, null);
        Type type = new TypeToken<List<Post>>() {}.getType();
        return gson.fromJson(json, type);
    }
}