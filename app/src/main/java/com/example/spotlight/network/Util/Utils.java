package com.example.spotlight.network.Util;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

public class Utils {
    public static void showJson (String text) {
        // Gson 객체 생성
        Gson gson = new Gson();
        // 객체를 JSON 문자열로 변환
        String jsonString = gson.toJson(text);
        // JSON 문자열 로그에 출력
        Log.d("JSON Output", jsonString);
    }

    public static void showJson (List list) {
        // Gson 객체 생성
        Gson gson = new Gson();
        // 객체를 JSON 문자열로 변환
        String jsonString = gson.toJson(list);
        // JSON 문자열 로그에 출력
        Log.d("JSON Output", jsonString);
    }

}
