package com.example.spotlight;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.SearchResponse;
import com.example.spotlight.network.Util.TokenManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> posts;
    private TextView searchResultTitle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        // TextView 초기화
        searchResultTitle = findViewById(R.id.search_result_title);

        // TokenManager 초기화
        TokenManager.initialize(this);

        // 리사이클러뷰 초기화
        recyclerView = findViewById(R.id.recyclerView_search_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 데이터 목록 생성 및 어댑터 설정
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(postAdapter);

        // Intent로부터 검색 결과 받기
        Intent intent = getIntent();
        String schoolName = intent.getStringExtra("schoolName");
        String majorName = intent.getStringExtra("majorName");
        String hashtag = intent.getStringExtra("hashtag");
        List<Post> searchResults = (List<Post>) intent.getSerializableExtra("searchResults");

        if (schoolName != null || majorName != null) {
            searchResultTitle.setText(schoolName + " " + majorName);
            searchPosts(schoolName, majorName);
        } else if (hashtag != null) {
            searchResultTitle.setText("#" + hashtag);
            searchByFeedHashtag(hashtag);
        }

        if (searchResults != null) {
            posts.clear();
            posts.addAll(searchResults);
            postAdapter.notifyDataSetChanged();
        }
    }

    public void onBackClicked(View view) {
        finish();
    }

    private void initializeData() {
        /*
        // 데이터 초기화 로직, 샘플 데이터 추가
        posts.add(new Post("@drawable/sample_image", "Project Title 1", "Category 1", "@drawable/image1",
                "Description of project 1.", 42, Arrays.asList("#tag1", "#tag2"), "@drawable/icon",false));
        posts.add(new Post("@drawable/sample_image", "Project Title 2", "Category 2", "@drawable/image2",
                "Description of project 2.", 52, Arrays.asList("#tag3", "#tag4"), "@drawable/icon",false));

         */
    }

    private void searchByFeedHashtag(String hashtag) {
        String accessToken = TokenManager.getToken(); // 액세스 토큰 가져오기

        if (accessToken == null || accessToken.isEmpty()) {
            Toast.makeText(this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<SearchResponse> call = apiService.searchByFeedHashtag(accessToken, hashtag);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Post> searchResults = response.body().getPosts();
                    posts.clear();
                    posts.addAll(searchResults);
                    postAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SearchResultActivity.this, "해시태그 검색에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(SearchResultActivity.this, "네트워크 오류입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchPosts(String schoolName, String majorName) {
        String accessToken = TokenManager.getToken(); // 액세스 토큰 가져오기

        if (accessToken == null || accessToken.isEmpty()) {
            Toast.makeText(this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<SearchResponse> call = apiService.searchPosts(accessToken, schoolName, majorName);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Post> searchResults = response.body().getPosts();
                    posts.clear();
                    posts.addAll(searchResults);
                    postAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SearchResultActivity.this, "학교/학과 검색에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(SearchResultActivity.this, "네트워크 오류입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
