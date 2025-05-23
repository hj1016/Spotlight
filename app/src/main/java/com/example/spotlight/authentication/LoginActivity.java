package com.example.spotlight.authentication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.R;
import com.example.spotlight.main.MainActivity;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.DTO.UserDTO;
import com.example.spotlight.network.Request.LoginRequest;
import com.example.spotlight.network.Response.LoginResponse;
import com.example.spotlight.network.Util.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;
    private ApiService apiService;
    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // 사용하는 레이아웃 변경

        // Initialize view components
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);

        apiService = ApiClient.getClient().create(ApiService.class);

        // 로그인 버튼 리스너
        loginButton.setOnClickListener(v -> loginUser());

        // 회원가입 버튼 리스너
        signupButton.setOnClickListener(v -> performSignUp());
    }

    private void loginUser() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "아이디와 패스워드를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();

                    if (loginResponse.getStatus() == 200) {
                        Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        Log.d("accessToken",loginResponse.getAccessToken());
                        TokenManager.setToken(loginResponse.getAccessToken());
                        TokenManager.setName(loginResponse.getName());
                        TokenManager.setUsername(loginResponse.getUsername());
                        TokenManager.setProfileImage(loginResponse.getProfileImage());
                        TokenManager.setServerId(loginResponse.getId());
                        TokenManager.setRole(loginResponse.getRole());

                        // 로그인 성공 후 MainActivity로 이동
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "로그인 실패: " + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "로그인 실패: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performSignUp() {
        Intent intent = new Intent(this, SignupStep1Activity.class);
        startActivity(intent);
    }
}