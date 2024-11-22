package com.example.spotlight.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.main.MainActivity;
import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Request.EmailSendingRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraduateStep1Activity extends AppCompatActivity {
    private EditText schoolEditText;
    private EditText majorEditText;
    private EditText emailEditText;
    private EditText codeEditText;
    private ApiService apiService;
    private String id;
    private String role = "STUDENT";
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graduate_step1);

        schoolEditText = findViewById(R.id.graduate_school_check_text);
        majorEditText = findViewById(R.id.graduate_major_check_text);
        emailEditText = findViewById(R.id.graduate_email_check_text);
        codeEditText = findViewById(R.id.graduate_num_check_text);

        apiService = ApiClient.getClient().create(ApiService.class);

        id = getIntent().getStringExtra("id");
        email = getIntent().getStringExtra("email");

        if (email != null) {
            emailEditText.setText(email);
            emailEditText.setEnabled(false);
        }
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, SignupStep5Activity.class);
        startActivity(intent);
    }

    public void onContinueGraduatesSignupClicked(View view) {
        String code = codeEditText.getText().toString().trim();
        if (code.isEmpty()) {
            Toast.makeText(this, "인증번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        EmailSendingRequest request = new EmailSendingRequest();
        request.setId(id);
        request.setRole(role);
        request.setSchool(schoolEditText.getText().toString().trim());
        request.setMajor(majorEditText.getText().toString().trim());
        request.setEmail(emailEditText.getText().toString().trim());
        request.setEmailCode(code);

        apiService.verificationCode(request).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String result = response.body();
                    if ("success".equals(result)) {
                        Toast.makeText(GraduateStep1Activity.this, "인증 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(GraduateStep1Activity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(GraduateStep1Activity.this, "인증 실패: " + result, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GraduateStep1Activity.this, "인증 실패: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(GraduateStep1Activity.this, "인증 에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onEmailCheckClicked(View view) {
        String email = emailEditText.getText().toString().trim();
        String school = schoolEditText.getText().toString().trim();
        String major = majorEditText.getText().toString().trim();

        if (email.isEmpty() || school.isEmpty() || major.isEmpty()) {
            Toast.makeText(this, "이메일, 학교명, 학과명을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        EmailSendingRequest request = new EmailSendingRequest();
        request.setEmail(email);
        request.setSchool(school);
        request.setMajor(major);
        request.setId(id);
        request.setRole(role);

        apiService.emailSending(request).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String result = response.body();
                    if ("success".equals(result)) {
                        Toast.makeText(GraduateStep1Activity.this, "이메일 발송 성공", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GraduateStep1Activity.this, "이메일 발송 실패: " + result, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GraduateStep1Activity.this, "이메일 발송 실패: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(GraduateStep1Activity.this, "이메일 발송 에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}