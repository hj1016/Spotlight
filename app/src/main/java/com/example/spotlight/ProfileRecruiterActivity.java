package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class ProfileRecruiterActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_profile_recruiter);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, MyPageRecruiterActivity.class);
        startActivity(intent);
    }

    public void onEditClicked(View view) {
        Intent intent = new Intent(this, ProfileRecruiterEditActivity.class);
        startActivity(intent);
    }
}
