package com.example.spotlight.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.DTO.UserRegistrationDTO;
import com.example.spotlight.network.Response.TokenResponse;
import com.example.spotlight.network.Util.TokenManager;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupStep5Activity extends AppCompatActivity {

    private RadioButton userSelec1, userSelec2;
    private RadioGroup radioGroupUserType;
    private SharedPreferences sharedPreferences;
    private ApiService apiService;
    private String email;
    private String id;
    private String password;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_step5);

        email = getIntent().getStringExtra("email");
        id = getIntent().getStringExtra("id");
        password = getIntent().getStringExtra("password");
        name = getIntent().getStringExtra("name");

        // 라디오 버튼 및 라디오 그룹 초기화
        userSelec1 = findViewById(R.id.User_selec1);
        userSelec2 = findViewById(R.id.User_selec2);
        radioGroupUserType = findViewById(R.id.radioGroupUserType);
        sharedPreferences = getSharedPreferences("UserType", MODE_PRIVATE);
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    // "Continue" 이미지 클릭 시 로직 처리
    public void onContinueClicked(View view) {
        int selectedId = radioGroupUserType.getCheckedRadioButtonId();

        String role;
        if (selectedId == R.id.User_selec1) {
            role = "STUDENT";
        } else if (selectedId == R.id.User_selec2){
            role = "RECRUITER";
        } else {
            Toast.makeText(this, "사용자 유형을 선택하세요", Toast.LENGTH_SHORT).show();
            return;
        }
        registerUser(role);
    }

    // "Skip" 이미지 클릭 시 MainActivity로 이동
    public void onSkipClicked(View view) {
        registerUser("NORMAL");
    }

    private void registerUser(String role) {
        UserRegistrationDTO registrationDto = new UserRegistrationDTO(email, id, password, name, role);

        /*
        apiService.registerUser(registrationDto).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                // Gson 객체 생성
                Gson gson = new Gson();
                // 객체를 JSON 문자열로 변환
                String jsonString = gson.toJson(response.body());
                // JSON 문자열 로그에 출력
                Log.d("JSON Output", jsonString);

                if (response.isSuccessful() && response.body() != null) {
                    TokenResponse tokenResponse = response.body();
                    if (tokenResponse.isSuccess()) {
                        Log.d("ROLE", role);
                        if (role.equals("STUDENT")) {
                            Log.d("STUDENT", "HERE");
                            Intent intent = new Intent(SignupStep5Activity.this, GraduateStep1Activity.class);
                            intent.putExtra("email", email);
                            intent.putExtra("id", id);
                            startActivity(intent);
                        } else if (role.equals("RECRUITER")) {
                            Log.d("RECRUITER", "HERE");
                            Intent intent = new Intent(SignupStep5Activity.this, RecruiterStep1Activity.class);
                            intent.putExtra("email", email);
                            intent.putExtra("id", id);
                            startActivity(intent);
                        } else {
                            TokenManager.setToken(tokenResponse.getAccessToken());
                            TokenManager.setUser(tokenResponse.getUser());
                            Toast.makeText(SignupStep5Activity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignupStep5Activity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(SignupStep5Activity.this, "회원가입 실패: " + tokenResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignupStep5Activity.this, "회원가입 실패: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(SignupStep5Activity.this, "회원가입 에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

         */
    }

}