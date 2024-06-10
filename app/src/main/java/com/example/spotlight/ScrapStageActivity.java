package com.example.spotlight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.network.Util.TokenManager;

public class ScrapStageActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrap_stage);
    }

    public void onBackClicked(View view) {
        String userType = TokenManager.getRole();
        Intent intent = new Intent(this, MainActivity.class);
        if ("RECRUITER".equals(userType)) {
            intent.putExtra("Fragment", "MyPageRecruiterFragment");
        } else {
            intent.putExtra("Fragment", "MyPageFragment");
        }
        startActivity(intent);
        finish();
    }

    public void onScrapProjectClicked(View view) {
        Intent intent = new Intent(this, ScrapProjectActivity.class);
        startActivity(intent);
    }
}
