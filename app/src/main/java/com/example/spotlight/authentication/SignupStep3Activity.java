package com.example.spotlight.authentication;

import android.content.Intent;
import android.os.Bundle;

import com.example.spotlight.R;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.DTO.PasswordValidationDTO;
import com.example.spotlight.network.DTO.PasswordValidationResponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupStep3Activity extends AppCompatActivity {
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_step3);

        passwordEditText = findViewById(R.id.editTextPassword);
        confirmPasswordEditText = findViewById(R.id.editTextPassword2);
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void onContinueClicked(View view) {
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        validatePassword(password);
    }

    private void validatePassword(String password) {
        PasswordValidationDTO passwordValidationDTO = new PasswordValidationDTO(password);
        apiService.validatePassword(passwordValidationDTO).enqueue(new Callback<PasswordValidationResponseDTO>() {
            @Override
            public void onResponse(Call<PasswordValidationResponseDTO> call, Response<PasswordValidationResponseDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PasswordValidationResponseDTO validationResponse = response.body();
                    if (validationResponse.isValid()) {
                        Intent intent = new Intent(SignupStep3Activity.this, SignupStep4Activity.class);
                        intent.putExtra("email", getIntent().getStringExtra("email"));
                        intent.putExtra("id", getIntent().getStringExtra("id"));
                        intent.putExtra("password", password);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignupStep3Activity.this, validationResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignupStep3Activity.this, "비밀번호 확인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PasswordValidationResponseDTO> call, Throwable t) {
                Toast.makeText(SignupStep3Activity.this, "오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
