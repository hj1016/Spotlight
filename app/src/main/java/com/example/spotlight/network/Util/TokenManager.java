package com.example.spotlight.network.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.android.jwt.JWT;

public class TokenManager {
    private static final String PREFS_NAME = "MyAppPrefs";
    private static final String TOKEN_KEY = "accessToken";

    private SharedPreferences sharedPreferences;

    public TokenManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    public void clearToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN_KEY);
        editor.apply();
    }

    // 토큰에서 ROLE 추출
    public String getRole() {
        String token = getToken();
        if (token == null) {
            return null;
        }
        try {
            JWT jwt = new JWT(token);
            return jwt.getClaim("role").asString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 토큰에서 USER_ID 추출
    public String getUserId() {
        String token = getToken();
        if (token == null) {
            return null;
        }
        try {
            JWT jwt = new JWT(token);
            return jwt.getClaim("userId").asString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 토큰에서 ID 추출
    public String getId() {
        String token = getToken();
        if (token == null) {
            return null;
        }
        try {
            JWT jwt = new JWT(token);
            return jwt.getClaim("id").asString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
