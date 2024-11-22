package com.example.spotlight.posting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Request.FeedRequest;
import com.example.spotlight.network.Response.DeleteResponse;
import com.example.spotlight.network.Response.FeedResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostingEditActivity extends AppCompatActivity {

    private ApiService apiService;
    private RecyclerView recyclerView;
    private MemberAdapter adapter;
    private List<Member> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_edit);

        apiService = ApiClient.getClientWithToken().create(ApiService.class);
        setupRecyclerView();
    }

    // 게시물 수정
    public void onCompletePostingEditClicked(View view) {
        // 게시물 ID 가져오기
        Intent intent = getIntent();
        Long feedId = (long) intent.getIntExtra("FEED_ID", -1);
        if (feedId == -1) {
            Toast.makeText(this, "잘못된 게시물 ID입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // UI에서 데이터 수집
        String title = ((EditText) findViewById(R.id.posting_edit_project_text)).getText().toString();
        String description = ((EditText) findViewById(R.id.posting_edit_description_text)).getText().toString();
        String bigCategory = ((Spinner) findViewById(R.id.posting_edit_big_category_spinner)).getSelectedItem().toString();
        String smallCategory = ((Spinner) findViewById(R.id.posting_edit_small_category_spinner)).getSelectedItem().toString();
        String hashtags = ((EditText) findViewById(R.id.posting_edit_hashtag_text)).getText().toString();

        if (title.isEmpty() || description.isEmpty() || bigCategory.isEmpty() || smallCategory.isEmpty()) {
            Toast.makeText(this, "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 요청 객체 생성
        FeedRequest feedRequest = new FeedRequest();
        feedRequest.setTitle(title);
        feedRequest.setDescription(description);
        feedRequest.setMainCategoryId(Long.parseLong(bigCategory));
        feedRequest.setSubCategoryId(Long.parseLong(smallCategory));

        // 해시태그 설정
        Set<String> hashtagSet = new HashSet<>(Arrays.asList(hashtags.split(" ")));
        feedRequest.setHashtags(hashtagSet);

        // 게시물 수정 요청
        Call<FeedResponse> call = apiService.updateFeed(feedId, feedRequest);
        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(PostingEditActivity.this, "게시물이 성공적으로 수정되었습니다!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PostingEditActivity.this, "게시물 수정에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                Toast.makeText(PostingEditActivity.this, "오류가 발생했습니다: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 게시물 삭제
    public void onDeleteClicked(View view) {
        Intent intent = getIntent();
        Long feedId = (long) intent.getIntExtra("FEED_ID", -1);
        if (feedId == -1) {
            Toast.makeText(this, "잘못된 게시물 ID입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("정말로 이 게시물을 삭제하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("예", (dialog, id) -> {
                    // 게시물 삭제 요청
                    Call<DeleteResponse> call = apiService.deleteFeed(feedId);
                    call.enqueue(new Callback<DeleteResponse>() {
                        @Override
                        public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                Toast.makeText(PostingEditActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(PostingEditActivity.this, "게시물 삭제에 실패했습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DeleteResponse> call, Throwable t) {
                            Toast.makeText(PostingEditActivity.this, "오류가 발생했습니다: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                })
                .setNegativeButton("아니오", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void setupRecyclerView() {
        memberList = new ArrayList<>();

        adapter = new MemberAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    public void onBackClicked(View view) {
        finish();
    }
}