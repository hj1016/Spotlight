package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.DTO.ExhibitionDTO;
import com.example.spotlight.network.Response.ExhibitionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPostingExhibitionActivity extends AppCompatActivity {

    private EditText locationEditText;
    private EditText scheduleEditText;
    private EditText timeEditText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_posting_exhibition_info);

        locationEditText = findViewById(R.id.new_posting_exhibition_location_text);
        scheduleEditText = findViewById(R.id.new_posting_exhibition_date_text);
        timeEditText = findViewById(R.id.new_posting_exhibition_time_text);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, NewPostingActivity.class);
        startActivity(intent);
    }

    public void onExhibitionInfoClicked(View view) {
        String location = locationEditText.getText().toString();
        String schedule = scheduleEditText.getText().toString();
        String time = timeEditText.getText().toString();

        ExhibitionDTO exhibitionDTO = new ExhibitionDTO(location, schedule, time);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ExhibitionResponse> call = apiService.createExhibition(exhibitionDTO);
        call.enqueue(new Callback<ExhibitionResponse>() {
            @Override
            public void onResponse(Call<ExhibitionResponse> call, Response<ExhibitionResponse> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent();
                    intent.putExtra("location", location);
                    intent.putExtra("schedule", schedule);
                    intent.putExtra("time", time);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    // 실패 시 처리
                    Toast.makeText(NewPostingExhibitionActivity.this, "전시 정보 작성에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ExhibitionResponse> call, Throwable t) {
                // 실패 시 처리
                Toast.makeText(NewPostingExhibitionActivity.this, "네트워크 오류입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}