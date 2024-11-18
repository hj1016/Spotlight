package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.MemberResponse;
import com.example.spotlight.network.Util.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailMemberGeneralActivity extends AppCompatActivity {

    private boolean isScrapped = false;

    private TextView memberNameTextView;
    private TextView memberRoleTextView;
    private TextView memberDepartmentTextView;
    private TextView memberProjectsTextView;
    private TextView memberProjectCategoryTextView; // 멤버의 분야는 프로필에서 따로 설정하는 부분이 없어서 프로젝트 분야만 설정함.

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_member_general);

        memberNameTextView = findViewById(R.id.item_detail_member_general_name);
        memberRoleTextView = findViewById(R.id.item_detail_member_general_role);
        memberDepartmentTextView = findViewById(R.id.item_detail_member_general_academic_ability_text);
        memberProjectsTextView = findViewById(R.id.item_detail_member_general_project_name);
        memberProjectCategoryTextView = findViewById(R.id.item_detail_member_general_project_category);

        // API 호출
        Integer userId = TokenManager.getUserId();
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<MemberResponse> call = apiService.getMembersByStudentId(userId);
        call.enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
                if (response.isSuccessful()) {
                    MemberResponse memberResponse = response.body();
                    if (memberResponse != null && memberResponse.isSuccess()) {
                        updateMemberDetails(memberResponse.getData());
                    } else {
                        Toast.makeText(ItemDetailMemberGeneralActivity.this, "팀원 정보 조회에 성공했습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("Member show", "fail");
                }
            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                Toast.makeText(ItemDetailMemberGeneralActivity.this, "네트워크 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }

     */

    private void updateMemberDetails(MemberResponse.MemberData memberData) {
        memberNameTextView.setText(memberData.getName());
        memberDepartmentTextView.setText(memberData.getDepartment());

        // 프로젝트 정보 설정
        StringBuilder projectsText = new StringBuilder();
        StringBuilder rolesText = new StringBuilder();
        StringBuilder categoriesText = new StringBuilder();

        for (MemberResponse.Project project : memberData.getProjects()) {
            projectsText.append(project.getName()).append("\n");
            rolesText.append(project.getRole()).append("\n");
            categoriesText.append(project.getCategory().getMain()).append("\n");
        }

        memberProjectsTextView.setText(projectsText.toString().trim());
        memberRoleTextView.setText(rolesText.toString().trim());
        memberProjectCategoryTextView.setText(categoriesText.toString().trim());
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        startActivity(intent);
    }

    public void toggleScrap(View view) {
        ImageView scrapButton = (ImageView) view;
        isScrapped = !isScrapped; // Toggle the state
        scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);
    }
}