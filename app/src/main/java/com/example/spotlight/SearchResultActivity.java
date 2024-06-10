package com.example.spotlight;

import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> posts;
    private TextView searchResultTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        searchResultTitle = findViewById(R.id.search_result_title);
        recyclerView = findViewById(R.id.recyclerView_search_result);

        String keyword = getIntent().getStringExtra("keyword");
        String school = getIntent().getStringExtra("school");
        String major = getIntent().getStringExtra("major");

        // 검색어와 학교&학과 정보가 모두 없는 경우
        if (keyword == null && (school == null || major == null)) {
            searchResultTitle.setText("검색어와 학교/학과 정보가 없습니다.");

            // 검색어가 있는 경우
        } else if (keyword != null) {
            searchResultTitle.setText(keyword);

            posts = new ArrayList<>();
            posts.add(new Post(R.drawable.icon1, "Hmm...", "시각 디자인", R.drawable.design,
                    " Look at her lips saying something. I can’t concentrate on what ...", 821, Arrays.asList("branding", "package", "sensuous"), R.drawable.scrap_no, false));
            postAdapter = new PostAdapter(this, posts);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(postAdapter);

            // 검색어는 없고 학교&학과 정보가 있는 경우
        } else {
            String title = school + " " + major;
            searchResultTitle.setText(title);

            posts = new ArrayList<>();
            posts.add(new Post(R.drawable.icon3, "DanDan", "소프트웨어", R.drawable.schoolmajorfeed,
                    "Let's Dance with the Characters! There's a new children's song ...", 617, Arrays.asList("App", "AR", "Software"), R.drawable.scrap_no, false));
            postAdapter = new PostAdapter(this, posts);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(postAdapter);
        }
    }


    // 뒤로 가기 버튼 클릭 시 호출되는 메서드
    public void onBackClicked(View view) {
        finish();
    }
}

    /*
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
        // 데이터 초기화 로직, 샘플 데이터 추가
        posts.add(new Post("@drawable/sample_image", "Project Title 1", "Category 1", "@drawable/image1",
                "Description of project 1.", 42, Arrays.asList("#tag1", "#tag2"), "@drawable/icon",false));
        posts.add(new Post("@drawable/sample_image", "Project Title 2", "Category 2", "@drawable/image2",
                "Description of project 2.", 52, Arrays.asList("#tag3", "#tag4"), "@drawable/icon",false));
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

     */