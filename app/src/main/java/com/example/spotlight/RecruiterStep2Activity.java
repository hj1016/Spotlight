package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RecruiterStep2Activity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruiter_step2);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, RecruiterStep1Activity.class);
        startActivity(intent);
    }

    public void onContinueRecruiterSignup2Clicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("shouldNavigateToHome", true);  // 인텐트에 플래그 추가
        startActivity(intent);
    }

}
