package com.example.spotlight;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Util.TokenManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScrapProjectActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> posts;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrap_project);

        // 리사이클러뷰 초기화
        recyclerView = findViewById(R.id.recyclerView_scrap_project);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 데이터 목록 생성 및 어댑터 설정
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(postAdapter);

        // API 호출
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<List<Post>> call = apiService.getScrapFeeds();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> feedList = response.body();
                    if (feedList != null) {
                        posts.clear();
                        posts.addAll(feedList);
                        postAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.e("ScrapProjectActivity", "Response not successful: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("ScrapProjectActivity", "API call failed", t);
            }
        });
    }

    public void onBackClicked(View view) {
        String userType = TokenManager.getRole();
        Intent intent = new Intent(this, MainActivity.class);
        if ("RECRUITER".equals(userType)) {
            intent.putExtra("Fragment", "MyPageRecruiterFragment");
        } else {
            intent.putExtra("Fragment", "MyPageFragment");
        }
        startActivity(intent);
        finish();
    }

    public void onScrapStageClicked(View view) {
        Intent intent = new Intent(this, ScrapStageActivity.class);
        startActivity(intent);
    }
}
