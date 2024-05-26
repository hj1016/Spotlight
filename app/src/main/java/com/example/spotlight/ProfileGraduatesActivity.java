package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class ProfileGraduatesActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_profile_graduates);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, MyPageActivity.class);
        startActivity(intent);
    }

    public void onEditClicked(View view) {
        Intent intent = new Intent(this, ProfileGraduatesEditActivity.class);
        startActivity(intent);
    }
}
