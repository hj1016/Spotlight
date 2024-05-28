package com.example.spotlight;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class MyPageRecruiterActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_recruiter);
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

    public void onScrapPostingClicked(View view) {
        Intent intent = new Intent(this, ScrapProjectActivity.class);
        startActivity(intent);
    }

    public void onScrapGraduatesClicked(View view) {
        Intent intent = new Intent(this, ScrapGraduatesActivity.class);
        startActivity(intent);
    }

    public void onProposeClicked(View view) {
        Intent intent = new Intent(this, RecruiterProposeActivity.class);
        startActivity(intent);
    }
}
