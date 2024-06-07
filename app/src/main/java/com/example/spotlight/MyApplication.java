package com.example.spotlight;

import android.app.Application;
import com.example.spotlight.network.Util.TokenManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // TokenManager 초기화
        TokenManager.initialize(this);
    }
}