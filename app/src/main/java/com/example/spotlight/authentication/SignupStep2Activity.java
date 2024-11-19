package com.example.spotlight.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Request.ExistIdRequest;
import com.example.spotlight.network.Response.ExistIdResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupStep2Activity extends AppCompatActivity {
    private EditText idEditText;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_step2);

        idEditText = findViewById(R.id.editTextID);
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void onContinueClicked(View view) {
        String id = idEditText.getText().toString().trim();
        if (!id.isEmpty()) {
            checkIdExistence(id);
        } else {
            Toast.makeText(this, "아이디를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkIdExistence(String id) {
        ExistIdRequest request = new ExistIdRequest();
        request.setId(id);

        apiService.existId(request).enqueue(new Callback<ExistIdResponse>() {
            @Override
            public void onResponse(Call<ExistIdResponse> call, Response<ExistIdResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ExistIdResponse existIdResponse = response.body();
                    if (!existIdResponse.isSuccess()) {
                        Intent intent = new Intent(SignupStep2Activity.this, SignupStep3Activity.class);
                        intent.putExtra("email", getIntent().getStringExtra("email"));
                        intent.putExtra("id", id);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignupStep2Activity.this, "아이디가 이미 존재합니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignupStep2Activity.this, "아이디 확인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ExistIdResponse> call, Throwable t) {
                Toast.makeText(SignupStep2Activity.this, "오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
