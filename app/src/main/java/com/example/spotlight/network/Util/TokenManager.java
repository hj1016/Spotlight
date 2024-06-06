package com.example.spotlight.network.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.android.jwt.JWT;

public class TokenManager {
    private static final String PREFS_NAME = "SPOTLIGHT_PREFS";
    private static final String TOKEN_KEY = "accessToken";

    private static SharedPreferences sharedPreferences;

    public static void initialize(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }
    }

    public static void setToken(String token) {
        if (sharedPreferences == null) {
            throw new IllegalStateException("\n" +
                    "TokenManager가 초기화되지 않았습니다. TokenManager.initialize(context)를 호출하여 초기화하세요.");
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public static String getToken() {
        if (sharedPreferences == null) {
            throw new IllegalStateException("\n" +
                    "TokenManager가 초기화되지 않았습니다. TokenManager.initialize(context)를 호출하여 초기화하세요.");
        }
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    public static void clearToken() {
        if (sharedPreferences == null) {
            throw new IllegalStateException("\n" +
                    "TokenManager가 초기화되지 않았습니다. TokenManager.initialize(context)를 호출하여 초기화하세요.");
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN_KEY);
        editor.apply();
    }

    // 토큰에서 ROLE 추출
    public static String getRole() {
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
    public static String getUserId() {
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
    public static String getId() {
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

    public static boolean isLoggedIn() {
        String token = getToken();
        if (token != null) {
            try {
                JWT jwt = new JWT(token);
                // 토큰이 만료되지 않았는지 확인
                return !jwt.isExpired(10); // 유효기간 만료를 확인. 10초 유예기간 줌.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
