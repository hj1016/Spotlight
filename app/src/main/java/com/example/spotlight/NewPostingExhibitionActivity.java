package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        // 이전 페이지에서 전달된 내용 유지
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            String hashtag = intent.getStringExtra("hashtag");
            int bigCategoryPosition = intent.getIntExtra("bigCategoryPosition", 0);
            int smallCategoryPosition = intent.getIntExtra("smallCategoryPosition", 0);

            // 유지할 내용을 EditText에 설정
            locationEditText.setText(title);
            scheduleEditText.setText(description);
            timeEditText.setText(hashtag);
        }
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onExhibitionInfoClicked(View view) {
        String location = locationEditText.getText().toString();
        String schedule = scheduleEditText.getText().toString();
        String time = timeEditText.getText().toString();

        ExhibitionDTO exhibitionDTO = new ExhibitionDTO(location, schedule, time);
        createExhibition(exhibitionDTO);
    }

    private void createExhibition(ExhibitionDTO exhibitionDTO){
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<ExhibitionResponse> call = apiService.createExhibition(exhibitionDTO);
        call.enqueue(new Callback<ExhibitionResponse>() {
            @Override
            public void onResponse(Call<ExhibitionResponse> call, Response<ExhibitionResponse> response) {
                if (response.isSuccessful()) {
                    // 서버에 전시 정보가 성공적으로 저장되었을 때
                    ExhibitionResponse exhibitionResponse = response.body();
                    if (exhibitionResponse != null && exhibitionResponse.isSuccess()) {
                        // 저장된 전시 정보를 받아와서 결과를 처리
                        ExhibitionDTO savedExhibition = exhibitionResponse.getExhibition();
                        if (savedExhibition != null) {
                            // 전시 정보를 성공적으로 저장한 경우
                            Toast.makeText(NewPostingExhibitionActivity.this, "전시 정보가 성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
                            // 이후 처리
                        } else {
                            // 전시 정보가 비어있는 경우
                            Toast.makeText(NewPostingExhibitionActivity.this, "전시 정보가 비어있습니다.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // 서버 응답이 실패한 경우
                        Toast.makeText(NewPostingExhibitionActivity.this, "전시 정보 저장에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 서버 응답이 실패한 경우
                    Toast.makeText(NewPostingExhibitionActivity.this, "서버 응답이 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ExhibitionResponse> call, Throwable t) {
                // 네트워크 오류 등의 이유로 서버에 요청을 보낼 수 없는 경우
                Toast.makeText(NewPostingExhibitionActivity.this, "서버에 요청을 보낼 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}