package com.example.spotlight.posting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagePostingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_posting);

        recyclerView = findViewById(R.id.recyclerView_manage_posting);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Post>> call = apiService.getMyFeeds();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Post> posts = response.body();

                    postAdapter = new PostAdapter(ManagePostingActivity.this, posts);
                    recyclerView.setAdapter(postAdapter);
                } else {
                    showError("피드 목록을 불러오는 데 실패했습니다.");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {  // 반환 타입에 맞게 수정
                Log.e("API Error", "Error: " + t.getMessage(), t);

                if (t instanceof java.net.SocketTimeoutException) {
                    showError("서버 응답이 지연되고 있습니다. 잠시 후 다시 시도해 주세요.");
                } else {
                    showError("네트워크 오류가 발생했습니다. 인터넷 연결을 확인해 주세요.");
                }
            }
        });
    }

    private void showError(String message) {
        Toast.makeText(ManagePostingActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void onNewPostingClicked(View view) {
        Intent intent = new Intent(this, NewPostingActivity.class);
        startActivity(intent);
    }

    public void onBackClicked(View view) {
        finish();
    }
}