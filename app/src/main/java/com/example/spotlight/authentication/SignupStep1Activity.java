package com.example.spotlight.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.R;

public class SignupStep1Activity extends AppCompatActivity {
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_step1);

        emailEditText = findViewById(R.id.editTextTextEmailAddress1);
    }

    public void onContinueClicked(View view) {
        String email = emailEditText.getText().toString().trim();
        if (!email.isEmpty()) {
            Intent intent = new Intent(this, SignupStep2Activity.class);
            intent.putExtra("email", email);
            startActivity(intent);
        } else {
            Toast.makeText(this, "이메일을 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}
