package com.example.spotlight;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Request.InvitationRequest;
import com.example.spotlight.network.Response.InvitationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPostingMemberActivity extends AppCompatActivity {

    private EditText memberIdEditText;
    private EditText roleEditText;
    private String memberId;
    private String role;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_posting_member_invite);

        memberIdEditText = findViewById(R.id.new_posting_member_ID_text);
        roleEditText = findViewById(R.id.new_posting_member_role_text);

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            String hashtag = intent.getStringExtra("hashtag");
            int bigCategoryPosition = intent.getIntExtra("bigCategoryPosition", 0);
            int smallCategoryPosition = intent.getIntExtra("smallCategoryPosition", 0);
        }
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onInviteMemberClicked(View view) {
        memberId = memberIdEditText.getText().toString();
        role = roleEditText.getText().toString();

        // API 호출
        // InvitationRequest 객체 생성 및 값 설정
        InvitationRequest invitationRequest = new InvitationRequest();
        invitationRequest.setProject_id("project_id"); // projectId 받아와야 함.
        invitationRequest.setMember_id(memberId);
        invitationRequest.setRole(role);

        // API 호출
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<InvitationResponse> call = apiService.inviteMemberToProject(invitationRequest);
        call.enqueue(new Callback<InvitationResponse>() {
            @Override
            public void onResponse(Call<InvitationResponse> call, Response<InvitationResponse> response) {
                if (response.isSuccessful()) {
                    InvitationResponse invitationResponse = response.body();
                    // 응답 처리
                    Toast.makeText(NewPostingMemberActivity.this, "팀원 초대에 성공했습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    // 실패 시 처리
                    Toast.makeText(NewPostingMemberActivity.this, "팀원 초대에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InvitationResponse> call, Throwable t) {
                // 실패 시 처리
                Toast.makeText(NewPostingMemberActivity.this, "네트워크 오류입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        Intent resultIntent = new Intent();
        resultIntent.putExtra("memberId", memberId);
        resultIntent.putExtra("role", role);

        setResult(RESULT_OK, resultIntent);
        finish();
    }
}