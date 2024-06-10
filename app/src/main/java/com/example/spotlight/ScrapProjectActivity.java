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
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        // 데이터 직접 입력
        posts.add(new Post(R.drawable.a_e_s, "You little human", "사진/영상", R.drawable.image_ex1,
                "On a blazingly sunny morning in March, the 22-year-old Italian tennis star Jannik Sinner could ...", 232, Arrays.asList("A.E.S", "Photo", "Photography"), R.drawable.scrap_no, true));
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
