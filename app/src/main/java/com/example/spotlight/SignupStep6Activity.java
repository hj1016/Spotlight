package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class SignupStep6Activity extends AppCompatActivity {
    // "Skip" 이미지 클릭 시 MainActivity로 이동
    public void onSkipClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // "Continue" 이미지 클릭 시 GraduateStep1Activity로 이동
    public void onContinueClicked(View view) {
        Intent intent = new Intent(this, GraduateStep1Activity.class);
        startActivity(intent);
    }
}
