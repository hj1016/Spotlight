package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RecruiterStep1Activity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruiter_step1);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, SignupStep5Activity.class);
        startActivity(intent);
    }

    public void onContinueClicked(View view) {
        Intent intent = new Intent(this, RecruiterStep2Activity.class);
        startActivity(intent);
    }
}
