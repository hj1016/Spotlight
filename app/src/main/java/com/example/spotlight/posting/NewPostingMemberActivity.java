package com.example.spotlight.posting;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.example.spotlight.R;
//import com.example.spotlight.posting.NewPostingActivity;

public class NewPostingMemberActivity extends AppCompatActivity {

    private EditText memberIdEditText;
    private EditText roleEditText;
    private int projectId;
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
            projectId = intent.getIntExtra("projectId", -1);
        }
    }

    /*
    public void onBackClicked(View view) {
        Intent intent = new Intent(this, NewPostingActivity.class);
        startActivity(intent);
    }

     */

    /*
    public void onInviteMemberClicked(View view) {
        memberId = memberIdEditText.getText().toString();
        role = roleEditText.getText().toString();

        if (memberId.isEmpty() || role.isEmpty()) {
            Toast.makeText(this, "팀원 정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Integer userId = TokenManager.getUserId();
        if (userId == null) {
            Toast.makeText(this, "사용자 ID를 찾을 수 없습니다. 다시 로그인 해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        inviteMember(projectId, memberId, role);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("memberId", memberId);
        resultIntent.putExtra("role", role);

        setResult(RESULT_OK, resultIntent);
        finish();
    }

     */

    /*
    // API 호출 메서드
    private void inviteMember(int projectId, String memberId, String memberRole) {
        InvitationRequest invitationRequest = new InvitationRequest();
        invitationRequest.setProjectId(projectId);
        invitationRequest.setMemberId(memberId);

        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<InvitationResponse> call = apiService.inviteMemberToProject(invitationRequest);
        call.enqueue(new Callback<InvitationResponse>() {
            @Override
            public void onResponse(Call<InvitationResponse> call, Response<InvitationResponse> response) {
                if (response.isSuccessful()) {
                    InvitationResponse invitationResponse = response.body();
                    Toast.makeText(NewPostingMemberActivity.this, "팀원 초대에 성공했습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewPostingMemberActivity.this, "팀원 초대에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InvitationResponse> call, Throwable t) {
                Toast.makeText(NewPostingMemberActivity.this, "네트워크 오류입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

     */
}