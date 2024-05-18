package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;


public class SignupStep1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_step1);
    }

    public void onContinueClicked(View view) {
        Intent intent = new Intent(this, SignupStep2Activity.class);
        startActivity(intent);
    }
}
