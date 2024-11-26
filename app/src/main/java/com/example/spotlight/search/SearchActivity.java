package com.example.spotlight.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.DTO.FeedDTO;
import com.example.spotlight.network.Util.TokenManager;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private EditText searchBarText;
    private ImageView searchButton;
    private LinearLayout historyContainer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        searchBarText = findViewById(R.id.search_bar_text);
        historyContainer = findViewById(R.id.history_container);
        searchButton = findViewById(R.id.search_button);

        // 검색 기록 불러오기
        loadSearchHistory();
    }

    public void onSearchButtonClicked(View view) {
        String hashtag = searchBarText.getText().toString().trim(); // 검색어 가져오기
        if (!hashtag.isEmpty()) {
            searchFeedsWithHashtag(hashtag); // 검색 실행
        } else {
            Toast.makeText(this, "해시태그를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isTokenValid() {
        String accessToken = TokenManager.getToken();
        if (accessToken.isEmpty()) {
            Toast.makeText(this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // 검색창에서 해시태그 검색
    private void searchFeedsWithHashtag(String hashtag) {
        if (!isTokenValid()) return;

        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<List<FeedDTO>> call = apiService.searchFeedsWithHashtag(hashtag);

        call.enqueue(new Callback<List<FeedDTO>>() {
            @Override
            public void onResponse(Call<List<FeedDTO>> call, Response<List<FeedDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // 검색 결과 화면으로 이동
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("searchResults", (Serializable) response.body());
                    intent.putExtra("hashtag", hashtag);
                    startActivity(intent);
                } else {
                    Toast.makeText(SearchActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FeedDTO>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 검색 기록 불러오기
    private void loadSearchHistory() {
        if (!isTokenValid()) return;

        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<List<String>> call = apiService.getHashtagSearchHistory();

        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    displaySearchHistory(response.body());
                } else {
                    Toast.makeText(SearchActivity.this, "검색 기록을 가져오는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 검색 기록 표시
    private void displaySearchHistory(List<String> history) {
        historyContainer.removeAllViews();

        for (String item : history) {
            if (isHistoryAlreadyDisplayed(item)) continue; // 중복 방지

            final String searchTerm = item;

            LinearLayout historyItemLayout = new LinearLayout(this);
            historyItemLayout.setOrientation(LinearLayout.HORIZONTAL);
            historyItemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            // 텍스트뷰 생성
            TextView textView = new TextView(this);
            textView.setText(item);
            textView.setTextSize(16);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1
            ));
            historyItemLayout.addView(textView);

            // 클릭 시 검색 실행
            // 클릭 이벤트로 검색 기록 API 호출
            historyItemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchBySearchHistory(searchTerm); // 검색 기록 API 호출
                }
            });

            // 이미지뷰 생성 (검색 아이콘)
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.search_bar);
            historyItemLayout.addView(imageView);

            historyContainer.addView(historyItemLayout);
        }
    }

    private boolean isHistoryAlreadyDisplayed(String item) {
        for (int i = 0; i < historyContainer.getChildCount(); i++) {
            View child = historyContainer.getChildAt(i);
            if (child instanceof LinearLayout) {
                TextView textView = ((LinearLayout) child).findViewById(R.id.history_text1);
                if (textView != null && textView.getText().toString().equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void searchBySearchHistory(String searchTerm) {
        if (!isTokenValid()) return;

        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<List<FeedDTO>> call = apiService.searchFeedsBySearchHistory(searchTerm);

        call.enqueue(new Callback<List<FeedDTO>>() {
            @Override
            public void onResponse(Call<List<FeedDTO>> call, Response<List<FeedDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // 검색 결과 화면으로 이동
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("searchResults", (Serializable) response.body());
                    intent.putExtra("searchTerm", searchTerm);
                    startActivity(intent);
                } else {
                    Toast.makeText(SearchActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FeedDTO>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onSearchSchoolClicked(View view) {
        Intent intent = new Intent(this, SearchSchoolActivity.class);
        startActivity(intent);
    }
}