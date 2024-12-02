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

public class ItemDetailMemberGeneralActivity extends AppCompatActivity {

    private TextView memberNameTextView, memberRoleTextView, memberDepartmentTextView;
    private ImageView memberImageView;
    private LinearLayout projectContainer;
    private FirebaseStorage firebaseStorage;
    private Long feedId, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_member_general);

        // View 초기화
        initializeViews();

        // Firebase Storage 초기화
        firebaseStorage = FirebaseStorage.getInstance();

        // Intent에서 데이터 가져오기
        handleIntentData();

        // 데이터가 유효하면 멤버 세부 정보 가져오기
        if (isValidData(feedId, userId)) {
            fetchMemberDetails(feedId, userId);
        } else {
            Toast.makeText(this, "잘못된 요청입니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initializeViews() {
        memberNameTextView = findViewById(R.id.item_detail_member_general_name);
        memberRoleTextView = findViewById(R.id.item_detail_member_general_role);
        memberDepartmentTextView = findViewById(R.id.item_detail_member_general_academic_ability_text);
        memberImageView = findViewById(R.id.item_detail_member_general_image);
        projectContainer = findViewById(R.id.item_detail_member_general_project_text_container);
    }

    private void handleIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            feedId = intent.getLongExtra("feedId", -1L);
            userId = intent.getLongExtra("userId", -1L);

            if (feedId == -1L || userId == -1L) {
                Toast.makeText(this, "잘못된 요청입니다.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Log.d("Intent Data", "FeedId: " + feedId + ", UserId: " + userId);
            }
        }
    }

    private boolean isValidData(Long feedId, Long userId) {
        return feedId != null && userId != null && feedId != -1L && userId != -1L;
    }

    private void fetchMemberDetails(Long feedId, Long userId) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<MemberDTO> call = apiService.getProjectTeamMemberInfo(feedId, userId);

        call.enqueue(new Callback<MemberDTO>() {
            @Override
            public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateMemberDetails(response.body());
                } else {
                    Toast.makeText(ItemDetailMemberGeneralActivity.this, "팀원 정보를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
                    Log.e("API Error", "Response Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MemberDTO> call, Throwable t) {
                Toast.makeText(ItemDetailMemberGeneralActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Network Error", t.getMessage(), t);
            }
        });
    }

    private void updateMemberDetails(MemberDTO member) {
        // 이름, 역할 및 학과 정보 설정
        memberNameTextView.setText(member.getName());
        memberDepartmentTextView.setText(member.getSchool() + " " + member.getMajor());

        String category = member.getProjects().get(0).getCategory(); // 첫 번째 프로젝트의 category 가져오기
        memberRoleTextView.setText(category); // TextView에 category 값 설정

        // 멤버 이미지 설정 (멤버의 첫 번째 프로젝트의 썸네일을 사용)
        if (member.getProjects() != null && !member.getProjects().isEmpty()) {
            // 첫 번째 프로젝트의 썸네일 이미지 로드
            String firstThumbnail = member.getProjects().get(0).getThumbnailImage();
            loadFirebaseImage(firstThumbnail, memberImageView);
        } else {
            // 프로젝트가 없는 경우 기본 이미지 사용
            memberImageView.setImageResource(R.drawable.team_image);
        }

        // 기존 프로젝트 정보를 모두 제거
        projectContainer.removeAllViews();

        // 각 프로젝트 정보를 동적으로 추가
        if (member.getProjects() != null) {
            for (MemberDTO.ProjectInfoDTO project : member.getProjects()) {
                addProjectView(project);
            }
        }
    }

    private void addProjectView(MemberDTO.ProjectInfoDTO project) {
        View projectView = getLayoutInflater().inflate(R.layout.item_project, projectContainer, false);

        TextView projectName = projectView.findViewById(R.id.item_project_name);
        TextView projectCategory = projectView.findViewById(R.id.item_project_category);
        ImageView projectImage = projectView.findViewById(R.id.item_project_photo);

        projectName.setText(project.getTitle());
        projectCategory.setText(project.getCategory());
        loadFirebaseImage(project.getThumbnailImage(), projectImage);

        projectContainer.addView(projectView);
    }

    private void loadFirebaseImage(String path, ImageView imageView) {
        if (path == null || path.isEmpty()) {
            imageView.setImageResource(R.drawable.team_image);
            return;
        }

        StorageReference imageRef = firebaseStorage.getReference().child(path);
        imageRef.getDownloadUrl().addOnSuccessListener(uri -> Glide.with(this)
                        .load(uri)
                        .placeholder(R.drawable.team_image)
                        .into(imageView))
                .addOnFailureListener(e -> {
                    Log.e("Firebase Error", "Image load failed: " + e.getMessage());
                    imageView.setImageResource(R.drawable.team_image);
                });
    }

    public void onBackClicked(View view) {
        finish();
    }
}