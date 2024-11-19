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

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Request.FeedRequest;
import com.example.spotlight.network.Response.DeleteResponse;
import com.example.spotlight.network.Response.FeedResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
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
        int feedId = intent.getIntExtra("FEED_ID", -1); // -1은 기본값으로 설정하여 만약 ID가 전달되지 않으면 오류 방지.

        // UI에서 데이터 수집
        String title = ((EditText) findViewById(R.id.posting_edit_project_text)).getText().toString();
        String content = ((EditText) findViewById(R.id.posting_edit_description_text)).getText().toString();
        String bigCategory = ((Spinner) findViewById(R.id.posting_edit_big_category_spinner)).getSelectedItem().toString();
        String smallCategory = ((Spinner) findViewById(R.id.posting_edit_small_category_spinner)).getSelectedItem().toString();
        String hashtags = ((EditText) findViewById(R.id.posting_edit_hashtag_text)).getText().toString();

        // 요청 객체 생성
        FeedRequest feedRequest = new FeedRequest();
        feedRequest.setTitle(title);
        feedRequest.setContent(content);
        feedRequest.setScrap(0);  // 기본값

        // 카테고리 설정
        FeedRequest.Category category = new FeedRequest.Category();
        category.setMain(bigCategory);
        category.setSub(smallCategory);
        feedRequest.setCategory(category);

        // 해시태그 설정
        List<String> hashtagList = Arrays.asList(hashtags.split(" "));
        feedRequest.setHashtag(hashtagList);

        // 게시물 수정 요청
        Call<FeedResponse> call = apiService.updateFeed(feedId, feedRequest);
        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(PostingEditActivity.this, "게시물이 성공적으로 수정되었습니다!", Toast.LENGTH_SHORT).show();
                    // 게시물 수정 후 현재 화면 종료
                    finish();
                } else {
                    Toast.makeText(PostingEditActivity.this, "게시물 수정에 실패했습니다", Toast.LENGTH_SHORT).show();
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
        int feedId = intent.getIntExtra("FEED_ID", -1); // -1은 기본값으로 설정하여 만약 ID가 전달되지 않으면 오류 방지.

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("정말로 이 게시물을 삭제하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 게시물 삭제 요청
                        Call<DeleteResponse> call = apiService.deleteFeed(feedId);
                        call.enqueue(new Callback<DeleteResponse>() {
                            @Override
                            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    Toast.makeText(PostingEditActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    // 게시물 삭제 후 현재 화면 종료
                                    finish();
                                } else {
                                    Toast.makeText(PostingEditActivity.this, "게시물 삭제에 실패했습니다", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                                Toast.makeText(PostingEditActivity.this, "오류가 발생했습니다: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void setupRecyclerView() {
        memberList = new ArrayList<>();
        // memberList.add(new Member(R.drawable.member_image, "김이름"));
        // memberList.add(new Member(R.drawable.member_image, "이이름"));
        // memberList.add(new Member(R.drawable.member_image, "박이름"));

        adapter = new MemberAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    public void onBackClicked(View view) {
        finish();
    }
}

 */