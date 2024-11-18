package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Request.EmailSendingRequest;
import com.example.spotlight.network.Response.EmailSendingResponse;
import com.example.spotlight.network.Response.TokenResponse;
import com.example.spotlight.network.Util.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecruiterStep1Activity extends AppCompatActivity {
    private EditText companyEditText;
    private EditText emailEditText;
    private EditText codeEditText;
    private ApiService apiService;
    private String id;
    private String role = "RECRUITER";
    private String email;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruiter_step1);
        Log.d("recruiter", "1");

        companyEditText = findViewById(R.id.recruiter_company_check_text);
        emailEditText = findViewById(R.id.recruiter_email_check_text);
        codeEditText = findViewById(R.id.recruiter_num_check_text);

        apiService = ApiClient.getClient().create(ApiService.class);

        id = getIntent().getStringExtra("id");
        email = getIntent().getStringExtra("email");

        if (email != null) {
            emailEditText.setText(email);
            emailEditText.setEnabled(false);
        }
    }

    public void onBackClicked(View view) {
        Log.d("recruiter", "2");
        Intent intent = new Intent(this, SignupStep5Activity.class);
        startActivity(intent);
    }

    public void onContinueRecruiterSignup1Clicked(View view) {
        Log.d("recruiter", "3");
        String code = codeEditText.getText().toString().trim();
        String company = companyEditText.getText().toString().trim();
        if (code.isEmpty()) {
            Toast.makeText(this, "인증번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        EmailSendingRequest request = new EmailSendingRequest();
        request.setId(id);
        request.setRole(role);
        request.setCompany(company);
        request.setEmail(emailEditText.getText().toString().trim());
        request.setEmailCode(code);

        apiService.verificationCode(request).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TokenResponse tokenResponse = response.body();
                    if (tokenResponse.isSuccess()) {
                        Toast.makeText(RecruiterStep1Activity.this, "인증 성공", Toast.LENGTH_SHORT).show();
                        TokenManager.setToken(tokenResponse.getAccessToken());
                        TokenManager.setUser(tokenResponse.getUser());
                        Log.d("RecruiterStep1", "Token set and starting RecruiterStep2Activity");
                        Intent intent = new Intent(RecruiterStep1Activity.this, RecruiterStep2Activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RecruiterStep1Activity.this, "인증 실패: " + tokenResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RecruiterStep1Activity.this, "인증 실패: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(RecruiterStep1Activity.this, "인증 에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onEmailCheckClicked(View view) {
        Log.d("recruiter", "4");
        String email = emailEditText.getText().toString().trim();
        String company = companyEditText.getText().toString().trim();

        if (email.isEmpty() || company.isEmpty()) {
            Toast.makeText(this, "이메일과 회사명을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        EmailSendingRequest request = new EmailSendingRequest();
        request.setEmail(email);
        request.setCompany(company);
        request.setId(id);
        request.setRole(role);
        Log.d("request", request.toString());
        Log.d("request", request.getId());
        Log.d("request", request.getRole());
        Log.d("request", request.getEmail());
        Log.d("request", request.getCompany());

        apiService.emailSending(request).enqueue(new Callback<EmailSendingResponse>() {
            @Override
            public void onResponse(Call<EmailSendingResponse> call, Response<EmailSendingResponse> response) {
                Log.d("recruiter", "5");
                Log.d("response", response.toString());
                if (response.isSuccessful() && response.body() != null) {
                    EmailSendingResponse emailSendingResponse = response.body();
                    if (emailSendingResponse.isSuccess()) {
                        Toast.makeText(RecruiterStep1Activity.this, "이메일 발송 성공", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RecruiterStep1Activity.this, "이메일 발송 실패", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RecruiterStep1Activity.this, "이메일 발송 실패: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EmailSendingResponse> call, Throwable t) {
                Log.d("recruiter", "6");
                Toast.makeText(RecruiterStep1Activity.this, "이메일 발송 에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

     */
}
