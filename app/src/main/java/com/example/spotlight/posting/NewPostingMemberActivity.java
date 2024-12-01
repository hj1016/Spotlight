package com.example.spotlight.posting;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.InvitationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPostingMemberActivity extends AppCompatActivity {

    private EditText memberIdEditText;
    private EditText roleEditText;
    private Long projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_posting_member_invite);

        memberIdEditText = findViewById(R.id.new_posting_member_ID_text);
        roleEditText = findViewById(R.id.new_posting_member_role_text);

        // 프로젝트 ID 가져오기
        Intent intent = getIntent();
        if (intent != null) {
            projectId = intent.getLongExtra("projectId", -1L);
        }
    }

    public void onBackClicked(View view) {
        finish(); // 현재 Activity 종료
    }

    public void onInviteMemberClicked(View view) {
        String memberName = memberIdEditText.getText().toString().trim();
        String role = roleEditText.getText().toString().trim();

        if (memberName.isEmpty() || role.isEmpty()) {
            Toast.makeText(this, "팀원 정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (projectId == null || projectId == -1L) {
            Toast.makeText(this, "유효한 프로젝트 ID가 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 팀원 초대 API 호출
        inviteMember(projectId, memberName, role);
    }

    private void inviteMember(Long projectId, String username, String role) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<InvitationResponse> call = apiService.inviteTeamMember(projectId, username, role);
        call.enqueue(new Callback<InvitationResponse>() {
            @Override
            public void onResponse(Call<InvitationResponse> call, Response<InvitationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    InvitationResponse invitationResponse = response.body();
                    if (invitationResponse.isSuccess()) {
                        // 초대 성공
                        Toast.makeText(NewPostingMemberActivity.this, "팀원 초대에 성공했습니다!", Toast.LENGTH_SHORT).show();

                        // 결과 전달
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("memberName", invitationResponse.getMemberName());
                        resultIntent.putExtra("role", invitationResponse.getMemberRole());
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    } else {
                        // 초대 실패 메시지 표시
                        Toast.makeText(NewPostingMemberActivity.this, "초대 실패: " + invitationResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 서버 응답 실패
                    Toast.makeText(NewPostingMemberActivity.this, "초대 실패: 서버 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InvitationResponse> call, Throwable t) {
                // 네트워크 오류 처리
                Toast.makeText(NewPostingMemberActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}