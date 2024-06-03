package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GraduateStep1Activity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graduate_step1);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, SignupStep5Activity.class);
        startActivity(intent);
    }

    public void onContinueGraduatesSignupClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
