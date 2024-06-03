package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupStep4Activity extends AppCompatActivity {
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_step4);

        nameEditText = findViewById(R.id.editTextName);
    }

    public void onContinueClicked(View view) {
        String name = nameEditText.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(SignupStep4Activity.this, SignupStep5Activity.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("id", getIntent().getStringExtra("id"));
        intent.putExtra("password", getIntent().getStringExtra("password"));
        intent.putExtra("name", name);
        startActivity(intent);
    }
}
