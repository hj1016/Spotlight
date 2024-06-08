package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagePostingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_posting);

        recyclerView = findViewById(R.id.recyclerView_manage_posting);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        postList = new ArrayList<>();
        // Add sample posts to the list
        postList.add(new Post(
                "team_image_url",          // teamImageUrl
                "Title",                               // title
                "Category",                            // category
                "image_url",                           // imageUrl
                "Content",                             // content
                5,                                     // scrap
                Arrays.asList("hashtag1", "hashtag2"), // hashtags
                "scrap_image_url",                     // scrapImageUrl
                false                                  // isScrapped
        ));

        postAdapter = new PostAdapter(this, postList);
        recyclerView.setAdapter(postAdapter);
    }

    public void onNewPostingClicked(View view) {
        Intent intent = new Intent(this, NewPostingActivity.class);
        startActivity(intent);
    }

    public void onBackClicked(View view) {
        finish();
    }
}