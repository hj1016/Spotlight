package com.example.spotlight.posting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.DTO.MemberDTO;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailMemberRecruiterActivity extends AppCompatActivity {

    // 뷰 변수 선언
    private TextView memberNameTextView;
    private TextView memberRoleTextView;
    private TextView memberDepartmentTextView;
    private ImageView memberImageView;
    private LinearLayout projectContainer;

    private Long feedId;
    private Long userId;

    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_member_recruiter);

        // 뷰 초기화
        memberNameTextView = findViewById(R.id.item_detail_member_recruiter_name);
        memberRoleTextView = findViewById(R.id.item_detail_member_recruiter_role);
        memberDepartmentTextView = findViewById(R.id.item_detail_member_recruiter_academic_ability_text);
        memberImageView = findViewById(R.id.item_detail_member_recruiter_image);
        projectContainer = findViewById(R.id.item_detail_member_recruiter_project_text_container);

        // Firebase Storage 초기화
        firebaseStorage = FirebaseStorage.getInstance();

        // Intent에서 feedId와 userId 가져오기
        Intent intent = getIntent();
        if (intent != null) {
            feedId = intent.getLongExtra("feedId", -1L);
            userId = intent.getLongExtra("userId", -1L);
        }

        if (feedId == -1L || userId == -1L) {
            Toast.makeText(this, "잘못된 요청입니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 멤버 세부 정보 가져오기
        fetchMemberDetails(feedId, userId);
    }

    private void fetchMemberDetails(Long feedId, Long userId) {
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<MemberDTO> call = apiService.getProjectTeamMemberInfo(feedId, userId);

        call.enqueue(new Callback<MemberDTO>() {
            @Override
            public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // 성공적으로 데이터를 가져오면 UI 업데이트
                    updateMemberDetails(response.body());
                } else {
                    Toast.makeText(ItemDetailMemberRecruiterActivity.this, "팀원 정보를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
                    Log.e("API Error", "Response Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MemberDTO> call, Throwable t) {
                Toast.makeText(ItemDetailMemberRecruiterActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Network Error", t.getMessage(), t);
            }
        });
    }

    private void updateMemberDetails(MemberDTO member) {
        // 멤버 이름, 학과 정보 설정
        memberNameTextView.setText(member.getName());
        memberRoleTextView.setText(member.getMajor());
        memberDepartmentTextView.setText(member.getSchool() + " " + member.getMajor());

        // 멤버 이미지 로드
        if (member.getProjects() != null && !member.getProjects().isEmpty()) {
            String thumbnail = member.getProjects().get(0).getThumbnailImage();
            loadFirebaseImage(thumbnail, memberImageView);
        } else {
            memberImageView.setImageResource(R.drawable.team_image);
        }

        // 프로젝트 정보 초기화 및 동적 추가
        projectContainer.removeAllViews();
        if (member.getProjects() != null) {
            for (MemberDTO.ProjectInfoDTO project : member.getProjects()) {
                addProjectView(project);
            }
        }
    }

    private void addProjectView(MemberDTO.ProjectInfoDTO project) {
        // 프로젝트 정보를 담을 동적 레이아웃 생성
        View projectView = getLayoutInflater().inflate(R.layout.item_project, projectContainer, false);

        // 프로젝트 제목 및 카테고리 설정
        TextView projectName = projectView.findViewById(R.id.item_project_name);
        TextView projectCategory = projectView.findViewById(R.id.item_project_category);
        projectName.setText(project.getTitle());
        projectCategory.setText(project.getCategory());

        // 프로젝트 이미지 로드
        ImageView projectImage = projectView.findViewById(R.id.item_project_photo);
        loadFirebaseImage(project.getThumbnailImage(), projectImage);

        // 프로젝트 정보를 컨테이너에 추가
        projectContainer.addView(projectView);
    }

    private void loadFirebaseImage(String path, ImageView imageView) {
        if (path == null || path.isEmpty()) {
            imageView.setImageResource(R.drawable.team_image);
            return;
        }

        StorageReference storageReference = firebaseStorage.getReference().child(path);
        storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(this)
                    .load(uri)
                    .placeholder(R.drawable.team_image)
                    .into(imageView);
        }).addOnFailureListener(e -> {
            Log.e("Firebase Error", "Image load failed: " + e.getMessage());
            imageView.setImageResource(R.drawable.team_image);
        });
    }

    public void onBackClicked(View view) {
        finish();
    }
}