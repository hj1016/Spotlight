package com.example.spotlight.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.DTO.FeedDTO;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchSchoolActivity extends AppCompatActivity {
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

        if (school.isEmpty() && major.isEmpty()) {
            Toast.makeText(this, "학교 또는 학과를 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        searchFeedsBySchoolOrMajor(school, major);
    }

    private void searchFeedsBySchoolOrMajor(String school, String major) {
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<List<FeedDTO>> call = apiService.searchFeedsBySchoolOrMajor(school, major);

        call.enqueue(new Callback<List<FeedDTO>>() {
            @Override
            public void onResponse(Call<List<FeedDTO>> call, Response<List<FeedDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<FeedDTO> results = response.body();

                    if (results.isEmpty()) {
                        Toast.makeText(SearchSchoolActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(SearchSchoolActivity.this, SearchResultActivity.class);
                        intent.putExtra("searchResults", (Serializable) results);
                        intent.putExtra("school", school);
                        intent.putExtra("major", major);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(SearchSchoolActivity.this, "검색에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FeedDTO>> call, Throwable t) {
                Toast.makeText(SearchSchoolActivity.this, "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackClicked(View view) {
        finish();
    }
}