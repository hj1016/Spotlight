package com.example.spotlight;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // 사용하는 레이아웃 변경

        // Initialize view components
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        signupButton = findViewById(R.id.signupButton);

        // 회원가입 버튼 리스너
        signupButton.setOnClickListener(v -> performSignUp());
    }

    private void performSignUp() {
        Intent intent = new Intent(this, SignupStep1Activity.class);
        startActivity(intent);
    }
}

