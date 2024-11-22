package com.example.spotlight.posting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Request.ExhibitionRequest;
import com.example.spotlight.network.Response.ExhibitionResponse;
import com.example.spotlight.network.Util.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPostingExhibitionActivity extends AppCompatActivity {

    private boolean isExhibitionInfoSaved = false;
    private EditText locationEditText;
    private EditText scheduleEditText;
    private EditText timeEditText;
    private String location;
    private String schedule;
    private String time;

    private Long feedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_posting_exhibition_info);

        locationEditText = findViewById(R.id.new_posting_exhibition_location_text);
        scheduleEditText = findViewById(R.id.new_posting_exhibition_date_text);
        timeEditText = findViewById(R.id.new_posting_exhibition_time_text);

        // 이전 페이지에서 전달된 내용 유지
        Intent intent = getIntent();
        if (intent != null) {
            feedId = intent.getLongExtra("feedId", -1L);
            if (feedId == -1L) {
                Toast.makeText(this, "유효하지 않은 피드 ID입니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onExhibitionInfoClicked(View view) {
        location = locationEditText.getText().toString();
        schedule = scheduleEditText.getText().toString();
        time = timeEditText.getText().toString();

        // 전시 정보가 올바르게 입력되었는지 확인
        if (location.isEmpty() || schedule.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "전시 정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Long userId = TokenManager.getServerId();
        if (userId == null) {
            Toast.makeText(this, "사용자 ID를 찾을 수 없습니다. 다시 로그인 해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // ExhibitionRequest 생성 및 데이터 설정
        ExhibitionRequest exhibitionRequest = new ExhibitionRequest();
        exhibitionRequest.setLocation(location);
        exhibitionRequest.setSchedule(schedule);
        exhibitionRequest.setTime(time);
        exhibitionRequest.setUserId(userId);
        exhibitionRequest.setFeedId(feedId);

        createExhibition(exhibitionRequest);
    }

    private void createExhibition(ExhibitionRequest exhibitionRequest) {
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<ExhibitionResponse> call = apiService.createExhibition(exhibitionRequest);
        call.enqueue(new Callback<ExhibitionResponse>() {
            @Override
            public void onResponse(Call<ExhibitionResponse> call, Response<ExhibitionResponse> response) {
                if (response.isSuccessful()) {
                    ExhibitionResponse exhibitionResponse = response.body();

                    if (exhibitionResponse != null && exhibitionResponse.isSuccess()) {
                        Toast.makeText(NewPostingExhibitionActivity.this, "전시 정보가 성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
                        isExhibitionInfoSaved = true;
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(NewPostingExhibitionActivity.this, "전시 정보 저장에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NewPostingExhibitionActivity.this, "서버 응답이 실패했습니다. 관리자에게 문의하세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ExhibitionResponse> call, Throwable t) {
                Toast.makeText(NewPostingExhibitionActivity.this, "서버에 연결할 수 없습니다. 인터넷 상태를 확인하세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isExhibitionInfoSaved() {
        return isExhibitionInfoSaved;
    }
}