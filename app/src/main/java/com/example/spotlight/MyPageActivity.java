package com.example.spotlight;

import android.os.Bundle;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;


public class MyPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_general);
    }

    public void onAlarmClicked(View view) {
        Intent intent = new Intent(this, AlarmActivity.class);
        startActivity(intent);
    }


    public void onProfileClicked(View view) {
        SharedPreferences prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String userType = prefs.getString("userType", "general"); // 기본값을 'general'로 설정

        Intent intent;
        if ("graduates".equals(userType)) {
            intent = new Intent(this, ProfileGraduatesActivity.class);
        } else { // 'general' 또는 그 외의 경우
            intent = new Intent(this, ProfileGeneralActivity.class);
        }

        startActivity(intent);
    }


    public void onManagePostingClicked(View view) {
        Intent intent = new Intent(this, ManagePostingActivity.class);
        startActivity(intent);
    }

    public void onPortfolioClicked(View view) {
        Intent intent = new Intent(this, MyPagePortfolioActivity.class);
        startActivity(intent);
    }

    public void onScrapPostingClicked(View view) {
        Intent intent = new Intent(this, ScrapProjectActivity.class);
        startActivity(intent);
    }

    public void onProposeClicked(View view) {
        Intent intent = new Intent(this, GraduatesProposeActivity.class);
        startActivity(intent);
    }




}
