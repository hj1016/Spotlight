package com.example.spotlight.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class SearchFragment extends Fragment {
    private EditText searchBarText;
    private ImageView searchButton;
    private LinearLayout historyContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_main, container, false);

        // View 초기화
        searchBarText = view.findViewById(R.id.search_bar_text);
        searchButton = view.findViewById(R.id.search_button);
        historyContainer = view.findViewById(R.id.history_container);

        // 학교 검색 버튼 클릭 이벤트 추가
        ImageView searchSchoolButton = view.findViewById(R.id.search_school);
        searchSchoolButton.setOnClickListener(v -> onSearchSchoolClicked());

        // 검색 기록 불러오기
        loadSearchHistory();

        // 검색 버튼 클릭 이벤트
        searchButton.setOnClickListener(v -> onSearchButtonClicked());

        return view;
    }

    private boolean isTokenValid() {
        String accessToken = TokenManager.getToken();
        if (accessToken.isEmpty()) {
            Toast.makeText(getContext(), "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // 검색 기록 불러오기
    private void loadSearchHistory() {
        if (!isTokenValid()) return;

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<String>> call = apiService.getHashtagSearchHistory();

        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    displaySearchHistory(response.body());
                } else {
                    Toast.makeText(getContext(), "검색 기록을 가져오는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 검색 기록 표시
    private void displaySearchHistory(List<String> history) {
        historyContainer.removeAllViews(); // 기존 검색 기록 제거

        for (String item : history) {
            if (isHistoryAlreadyDisplayed(item)) continue; // 중복 방지

            final String searchTerm = item;

            // 검색 이력의 컨테이너 (LinearLayout 생성)
            LinearLayout historyItemLayout = new LinearLayout(getContext());
            historyItemLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 16, 0, 16); // 좌우 마진 제거 (컨테이너는 전체 크기)
            historyItemLayout.setLayoutParams(layoutParams);

            // 텍스트뷰 생성
            TextView textView = new TextView(getContext());
            textView.setText("#" + item);
            textView.setTextSize(19); // 텍스트 크기
            textView.setTextColor(getResources().getColor(R.color.gray)); // 텍스트 색상 회색
            textView.setPadding(48, 16, 16, 16); // 좌측 패딩 조정 (History와 동일 위치)
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1 // weight=1로 확장
            ));
            textView.setId(View.generateViewId());

            // 폰트 설정
            textView.setTypeface(getResources().getFont(R.font.pretendard_bold)); // pretendard_bold 적용
            historyItemLayout.addView(textView);

            // 클릭 이벤트 추가 (검색 실행)
            historyItemLayout.setOnClickListener(v -> searchBySearchHistory(searchTerm));

            // 검색 아이콘 추가
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.search_go);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    52, 52 // 아이콘 크기 설정
            );
            imageParams.setMargins(16, 16, 16, 0); // 아이콘 위치 조정
            imageView.setLayoutParams(imageParams);
            historyItemLayout.addView(imageView);

            // 부모 컨테이너에 추가
            historyContainer.addView(historyItemLayout);
        }
    }

    private boolean isHistoryAlreadyDisplayed(String item) {
        for (int i = 0; i < historyContainer.getChildCount(); i++) {
            View child = historyContainer.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout historyItemLayout = (LinearLayout) child;
                if (historyItemLayout.getChildAt(0) instanceof TextView) {
                    TextView textView = (TextView) historyItemLayout.getChildAt(0);
                    if (textView.getText().toString().equals(item)) {
                        return true; // 이미 존재
                    }
                }
            }
        }
        return false;
    }

    private void searchBySearchHistory(String searchTerm) {
        if (!isTokenValid()) return;

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<FeedDTO>> call = apiService.searchFeedsBySearchHistory(searchTerm);

        call.enqueue(new Callback<List<FeedDTO>>() {
            @Override
            public void onResponse(Call<List<FeedDTO>> call, Response<List<FeedDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // 검색 결과 화면으로 이동
                    Intent intent = new Intent(getContext(), SearchResultActivity.class);
                    intent.putExtra("searchResults", (Serializable) response.body());
                    intent.putExtra("searchTerm", searchTerm);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FeedDTO>> call, Throwable t) {
                Toast.makeText(getContext(), "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onSearchButtonClicked() {
        // 입력값 가져오기
        String hashtag = searchBarText.getText().toString().trim();

        // # 제거 조건 처리
        hashtag = hashtag.startsWith("#") ? hashtag.substring(1) : hashtag;

        if (!hashtag.isEmpty()) {
            // 검색 실행
            searchFeedsWithHashtag(hashtag);
        } else {
            Toast.makeText(getContext(), "해시태그를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    private void searchFeedsWithHashtag(String hashtag) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<FeedDTO>> call = apiService.searchFeedsWithHashtag(hashtag);

        call.enqueue(new Callback<List<FeedDTO>>() {
            @Override
            public void onResponse(Call<List<FeedDTO>> call, Response<List<FeedDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // 검색 결과를 SearchResultActivity로 전달
                    Intent intent = new Intent(getContext(), SearchResultActivity.class);
                    intent.putExtra("searchResults", (Serializable) response.body());
                    intent.putExtra("hashtag", hashtag);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FeedDTO>> call, Throwable t) {
                Log.e("SearchFragment", "Error searching feeds: " + t.getMessage());
                Toast.makeText(getContext(), "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onSearchSchoolClicked() {
        Intent intent = new Intent(getContext(), SearchSchoolActivity.class);
        startActivity(intent);
    }
}