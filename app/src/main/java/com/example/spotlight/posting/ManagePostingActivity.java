package com.example.spotlight.posting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagePostingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_posting);

        recyclerView = findViewById(R.id.recyclerView_manage_posting);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);

        Call<List<Post>> call = apiService.getMyFeeds();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> feedList = response.body();
                    postAdapter = new PostAdapter(ManagePostingActivity.this, feedList);
                    recyclerView.setAdapter(postAdapter);

                    posts = new ArrayList<>();
                    posts.add(new Post(R.drawable.icon3, "DanDan", "소프트웨어", R.drawable.schoolmajorfeed,
                            "Let's Dance with the Characters! There's a new children's song ...", 617, Arrays.asList("App", "AR", "Software"), R.drawable.scrap_no, false));
                    postAdapter = new PostAdapter(ManagePostingActivity.this, posts);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ManagePostingActivity.this));
                    recyclerView.setAdapter(postAdapter);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    /*
    public void onNewPostingClicked(View view) {
        Intent intent = new Intent(this, NewPostingActivity.class);
        startActivity(intent);
    }

     */

    public void onBackClicked(View view) {
        finish();
    }
}