package com.example.spotlight;

import android.app.Application;
import android.util.Log;

import com.example.spotlight.network.Util.TokenManager;

import java.io.IOException;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // TokenManager 초기화
        TokenManager.initialize(this);
    }
}