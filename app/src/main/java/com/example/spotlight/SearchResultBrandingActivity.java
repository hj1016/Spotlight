package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchResultBrandingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private TextView searchResultTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_branding);

        searchResultTitle = findViewById(R.id.search_result_title);
        recyclerView = findViewById(R.id.recyclerView_search_result);

        String search = getIntent().getStringExtra("search");
        searchResultTitle.setText(search);

        // 리사이클러뷰에 포스트 데이터 표시 (테스트용 데이터 사용)
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(R.drawable.icon3, "Hmm...", "시각 디자인", R.drawable.icon1,
                "Look at her lips saying something. I can’t concentrate on what she’s saying. Start with a soft texture and taste savory.\n" +
                        "At the end, the sweetness lingers deeply. Open in January. Hac etiam convallis mattis diam ...", 1700, Arrays.asList("package", "branding", "sensuous"), R.drawable.scrap_no, false));
        postAdapter = new PostAdapter(this, posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }
}
