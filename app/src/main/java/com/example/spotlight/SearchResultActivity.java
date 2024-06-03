package com.example.spotlight;

import android.content.Intent;
import android.view.View;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> posts;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);


        // 리사이클러뷰 초기화
        recyclerView = findViewById(R.id.recyclerView_search_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 데이터 목록 생성 및 어댑터 설정
        posts = new ArrayList<>();
        initializeData();  // 데이터를 초기화하는 메서드, 아래에 구현 예시를 제공
        postAdapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(postAdapter);
    }

    private void initializeData() {
        // 데이터 초기화 로직, 샘플 데이터 추가
        posts.add(new Post("@drawable/sample_image", "Project Title 1", "Category 1", "@drawable/image1",
                "Description of project 1.", 42, Arrays.asList("#tag1", "#tag2"), "@drawable/icon",false));
        posts.add(new Post("@drawable/sample_image", "Project Title 2", "Category 2", "@drawable/image2",
                "Description of project 2.", 52, Arrays.asList("#tag3", "#tag4"), "@drawable/icon",false));
        // 더 많은 데이터 추가 가능
    }

    public void onBackClicked(View view) {
        finish();
    }
}
