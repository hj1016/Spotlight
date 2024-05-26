package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class ProfileGraduatesEditActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_profile_graduates_edit);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, ProfileGraduatesActivity.class);
        startActivity(intent);
    }

    public void onCompleteClicked(View view) {
        Intent intent = new Intent(this, ProfileGraduatesActivity.class);
        startActivity(intent);
    }
}
