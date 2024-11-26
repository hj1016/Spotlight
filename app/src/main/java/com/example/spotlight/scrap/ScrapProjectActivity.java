package com.example.spotlight.scrap;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;
import com.example.spotlight.main.MainActivity;
import com.example.spotlight.network.Util.TokenManager;
import com.example.spotlight.posting.Post;
import com.example.spotlight.posting.PostAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScrapProjectActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private List<Post> posts;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrap_project);

        // 리사이클러뷰 초기화
        recyclerView = findViewById(R.id.recyclerView_scrap_project);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 데이터 목록 생성
        posts = new ArrayList<>();

        // 어댑터 생성 및 설정
        adapter = new PostAdapter(this, posts);  // ScrapProjectActivity 객체(this) 전달
        recyclerView.setAdapter(adapter);

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
