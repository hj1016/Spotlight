package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.DTO.ExhibitionDTO;
import com.example.spotlight.network.Response.ExhibitionResponse;
import com.example.spotlight.network.Util.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPostingExhibitionActivity extends AppCompatActivity {

    private EditText locationEditText;
    private EditText scheduleEditText;
    private EditText timeEditText;
    private String location;
    private String schedule;
    private String time;

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
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            String hashtag = intent.getStringExtra("hashtag");
            int bigCategoryPosition = intent.getIntExtra("bigCategoryPosition", 0);
            int smallCategoryPosition = intent.getIntExtra("smallCategoryPosition", 0);
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

        Integer userId = TokenManager.getUserId();

        if (userId == null) {
            Toast.makeText(this, "사용자 ID를 찾을 수 없습니다. 다시 로그인 해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        ExhibitionDTO exhibitionDTO = new ExhibitionDTO(location, schedule, time, userId);
        createExhibition(exhibitionDTO);

        // 결과 설정
        Intent resultIntent = new Intent();
        resultIntent.putExtra("location", location);
        resultIntent.putExtra("schedule", schedule);
        resultIntent.putExtra("time", time);
        setResult(RESULT_OK, resultIntent);

        // 액티비티 종료
        finish();
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
                        // 전시 정보를 성공적으로 저장한 경우
                        Toast.makeText(NewPostingExhibitionActivity.this, "전시 정보가 성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show();

                        // 결과 설정
                        setResult(RESULT_OK);
                    } else {
                        // 서버 응답이 실패한 경우
                        Toast.makeText(NewPostingExhibitionActivity.this, "전시 정보 저장에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_CANCELED); // 실패 시 RESULT_CANCELED 전달
                    }
                } else {
                    // 서버 응답이 실패한 경우
                    Toast.makeText(NewPostingExhibitionActivity.this, "서버 응답이 실패했습니다.", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED); // 실패 시 RESULT_CANCELED 전달
                }
                // 액티비티 종료
                finish();
            }

            @Override
            public void onFailure(Call<ExhibitionResponse> call, Throwable t) {
                // 네트워크 오류 등의 이유로 서버에 요청을 보낼 수 없는 경우
                Toast.makeText(NewPostingExhibitionActivity.this, "서버에 요청을 보낼 수 없습니다.", Toast.LENGTH_SHORT).show();
                setResult(RESULT_CANCELED); // 실패 시 RESULT_CANCELED 전달
                // 액티비티 종료
                finish();
            }
        });

}
}