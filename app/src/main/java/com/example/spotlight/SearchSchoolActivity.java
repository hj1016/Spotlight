package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchSchoolActivity  extends AppCompatActivity {
    private EditText searchSchoolText;
    private EditText searchMajorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_school);

        searchSchoolText = findViewById(R.id.search_school_text);
        searchMajorText = findViewById(R.id.search_school_major_text);
    }

    public void onContinueSchoolSearchClicked(View view) {
        String school = searchSchoolText.getText().toString().trim();
        String major = searchMajorText.getText().toString().trim();

        if (!school.isEmpty() && !major.isEmpty()) {
            Intent intent = new Intent(this, SearchResultActivity.class);
            intent.putExtra("school", school);
            intent.putExtra("major", major);
            startActivity(intent);
        } else {
            Toast.makeText(this, "학교와 학과를 모두 입력하세요.", Toast.LENGTH_SHORT).show();
        }
    }
}


/*
    private ApiService apiService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_school);

        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onContinueSchoolSearchClicked(View view) {
        // 사용자가 입력한 학교와 학과 정보 가져오기
        EditText schoolEditText = findViewById(R.id.search_school_text);
        EditText majorEditText = findViewById(R.id.search_school_major_text);

        String school = schoolEditText.getText().toString();
        String major = majorEditText.getText().toString();

        // 학교와 학과 정보가 비어있지 않은지 확인
        if (!school.isEmpty() && !major.isEmpty()) {
            // API 호출을 위한 Call 객체 생성
            Call<SearchResponse> call = apiService.searchPosts("47479e98ee3da9a4dd5dcbdb6698d29f", school, major);
            call.enqueue(new Callback<SearchResponse>() {
                @Override
                public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // API 호출이 성공하면 SearchResultActivity로 이동하고 검색 결과를 전달
                        SearchResponse searchResponse = response.body();
                        Intent intent = new Intent(SearchSchoolActivity.this, SearchResultActivity.class);
                        intent.putExtra("searchResult", searchResponse);
                        startActivity(intent);
                    } else {
                        // API 호출이 실패한 경우 처리
                        Toast.makeText(SearchSchoolActivity.this, "검색 결과를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SearchResponse> call, Throwable t) {
                    // 네트워크 오류 처리
                    Toast.makeText(SearchSchoolActivity.this, "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // 학교나 학과 정보가 비어있는 경우 에러 메시지 표시
            Toast.makeText(SearchSchoolActivity.this, "학교와 학과를 입력하세요.", Toast.LENGTH_SHORT).show();
        }
    }
}

 */