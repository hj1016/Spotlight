package com.example.spotlight.search;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.spotlight.R;
import com.example.spotlight.posting.Post;
import com.example.spotlight.posting.PostAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchResultBrandingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_branding);

        recyclerView = findViewById(R.id.recyclerView_search_result_branding);


        // 리사이클러뷰에 포스트 데이터 표시 (테스트용 데이터 사용)
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(R.drawable.icon1, "Hmm...", "시각 디자인", R.drawable.design,
                "Look at her lips saying something. I can’t concentrate on what  ...", 430, Arrays.asList("package", "branding", "sensuous"), R.drawable.scrap_no, false));
        postAdapter = new PostAdapter(this, posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }

    public void onBackClicked(View view) {
        finish();
    }
}
