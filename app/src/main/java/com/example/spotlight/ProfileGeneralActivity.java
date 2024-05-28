package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class ProfileGeneralActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_profile_general);
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onEditClicked(View view) {
        Intent intent = new Intent(this, ProfileGeneralEditActivity.class);
        startActivity(intent);
    }
}
