package com.example.spotlight.application;

import android.app.Application;

import com.example.spotlight.network.Util.TokenManager;

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // TokenManager 초기화
        TokenManager.init(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }
}