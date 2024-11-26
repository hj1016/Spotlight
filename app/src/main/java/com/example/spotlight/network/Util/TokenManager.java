package com.example.spotlight.network.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

public class TokenManager {

    private static final String PREF_NAME = "user_pref";
    private static final String KEY_SERVER_ID = "server_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ROLE = "role";
    private static final String KEY_PROFILE_IMG = "profile_image";
    private static final String KEY_SCHOOL = "school";
    private static final String KEY_MAJOR = "major";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_TOKEN = "access_token";

    private static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
    }

    public static void saveUserProfile(long serverId, String username, String name, String email, String role, String profileImg, String school, String major, String company, String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(KEY_SERVER_ID, serverId);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_PROFILE_IMG, profileImg);
        editor.putString(KEY_SCHOOL, school);
        editor.putString(KEY_MAJOR, major);
        editor.putString(KEY_COMPANY, company);
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    public static void setToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    public static long getServerId() {
        return sharedPreferences.getLong(KEY_SERVER_ID, -1L);
    }

    public static String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    public static String getName() {
        return sharedPreferences.getString(KEY_NAME, "");
    }

    public static String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

    public static String getRole() {
        String token = getToken();
        try {
            String[] splitToken = token.split("\\.");
            if (splitToken.length < 2) {
                return "NORMAL";
            }
            String body = new String(android.util.Base64.decode(splitToken[1], android.util.Base64.DEFAULT));
            JSONObject jsonObject = new JSONObject(body);
            String role = jsonObject.getString("role");
            return role;
        } catch (Exception e) {
            Log.e("TokenManager", "토큰에서 ROLE을 추출하는 데 실패했습니다.", e);
            return "NORMAL";
        }
    }

    public static String getProfileImage() {
        return sharedPreferences.getString(KEY_PROFILE_IMG, "");
    }

    public static String getSchool() {
        return sharedPreferences.getString(KEY_SCHOOL, "");
    }

    public static String getMajor() {
        return sharedPreferences.getString(KEY_MAJOR, "");
    }

    public static String getCompany() {
        return sharedPreferences.getString(KEY_COMPANY, "");
    }

    public static String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, "");
    }

    public static void clearToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static void setServerId(long serverId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(KEY_SERVER_ID, serverId);
        editor.apply();
    }

    public static void setUsername(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public static void setName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public static void setEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public static void setRole(String role) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ROLE, role);
        editor.apply();
    }

    public static void setProfileImage(String profileImage) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PROFILE_IMG, profileImage);
        editor.apply();
    }

    public static void setSchool(String school) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SCHOOL, school);
        editor.apply();
    }

    public static void setMajor(String major) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_MAJOR, major);
        editor.apply();
    }

    public static void setCompany(String company) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_COMPANY, company);
        editor.apply();
    }

    public static void setTokenForKey(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getTokenForKey(String key) {
        return sharedPreferences.getString(key, null);
    }

    public static void clearTokenForKey(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

}