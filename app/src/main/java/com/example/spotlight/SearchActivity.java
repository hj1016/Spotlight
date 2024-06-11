package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    private EditText searchBarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        searchBarText = findViewById(R.id.search_bar_text);
    }

    // 검색 버튼 클릭 이벤트 핸들러
    public void onSearchButtonClicked(View view) {
//        String keyword = searchBarText.getText().toString().trim();
//
//        if (!keyword.isEmpty()) {
            // 검색 결과 액티비티로 이동하는 인텐트 생성
            Intent intent = new Intent(this, SearchResultBrandingActivity.class);
            startActivity(intent);
//        } else {
//            Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show();
//        }
    }

}

    /*

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        searchBarText = findViewById(R.id.search_bar_text);
        searchBar = findViewById(R.id.search_bar);
        historyContainer = findViewById(R.id.history_container);
        sharedPreferences = getSharedPreferences("SearchHistory", MODE_PRIVATE);

        // 이전에 저장된 검색 이력을 불러와서 화면에 추가
        loadSearchHistory();

        getHashtagHistory();

        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hashtag = searchBarText.getText().toString().trim();
                if (!hashtag.isEmpty()) {
                    addHistoryItem(hashtag); // 새로운 이력 추가
                    searchByHashtag(hashtag);
                }
            }
        });

        // 해시태그 검색 이력을 클릭했을 때 검색 수행
        historyContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView historyTextView = findViewById(R.id.history_text1);
                String hashtag = historyTextView.getText().toString().trim();
                if (!hashtag.isEmpty()) {
                    searchByHashtag(hashtag);
                }
            }
        });
    }

    private void searchByHashtag(String hashtag) {
        String accessToken = TokenManager.getToken();

        if (accessToken == null || accessToken.isEmpty()) {
            Toast.makeText(this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<SearchResponse> call = apiService.searchByHashtag(accessToken, hashtag);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("searchResults", (Serializable) response.body().getPosts());
                    intent.putExtra("hashtag", hashtag);
                    startActivity(intent);
                } else {
                    Toast.makeText(SearchActivity.this, "해시태그 검색에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "네트워크 오류입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getHashtagHistory() {
        String accessToken = TokenManager.getToken();

        if (accessToken == null || accessToken.isEmpty()) {
            Toast.makeText(this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<HashtagHistoryResponse> call = apiService.getHashtagHistory(accessToken);
        call.enqueue(new Callback<HashtagHistoryResponse>() {
            @Override
            public void onResponse(Call<HashtagHistoryResponse> call, Response<HashtagHistoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // API 응답에서 해시태그 검색 이력을 가져온 후 LinearLayout에 동적으로 텍스트뷰와 이미지뷰를 추가함.
                    String history = response.body().getHistory().toString();
                    addHistoryItem(history);
                } else {
                    Toast.makeText(SearchActivity.this, "해시태그 검색 이력을 가져오는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HashtagHistoryResponse> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "네트워크 오류입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadSearchHistory() {
        String history = sharedPreferences.getString("history", "");
        if (!history.isEmpty()) {
            String[] historyArray = history.split(",");
            for (String item : historyArray) {
                addHistoryItem(item);
            }
        }
    }

    private void addHistoryItem(String history) {
        // 이전에 추가한 이력을 SharedPreferences에 저장
        String savedHistory = sharedPreferences.getString("history", "");
        if (!savedHistory.isEmpty()) {
            savedHistory += ",";
        }
        savedHistory += history;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("history", savedHistory);
        editor.apply();

        // 새로운 이력을 보여주기 위한 새로운 LinearLayout 생성
        LinearLayout historyItemLayout = new LinearLayout(this);
        historyItemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        historyItemLayout.setOrientation(LinearLayout.HORIZONTAL);

        // 이력을 보여주는 TextView를 생성하고 추가
        TextView textView = new TextView(this);
        textView.setText(history);
        textView.setTextSize(20);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        ));
        historyItemLayout.addView(textView);

        // 클릭 리스너 설정
        historyItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByHashtag(history);
            }
        });

        // 검색 아이콘 이미지뷰를 생성하고 추가
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.search_go);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        historyItemLayout.addView(imageView);

        // 새로운 LinearLayout을 historyContainer에 추가
        historyContainer.addView(historyItemLayout);
    }

    public void onSearchSchoolClicked(View view) {
        Intent intent = new Intent(this, SearchSchoolActivity.class);
        startActivity(intent);
    }
    */