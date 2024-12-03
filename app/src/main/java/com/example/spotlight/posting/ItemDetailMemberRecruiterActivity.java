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
import com.example.spotlight.network.Response.ScrapResponse;
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

    private ImageView scrapButton;
    private boolean isScrapped = false;

    private Long feedId;
    private Long studentId;

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
        projectContainer = findViewById(R.id.item_detail_member_recruiter_project_container);
        scrapButton = findViewById(R.id.item_detail_member_recruiter_scrap_button);

        firebaseStorage = FirebaseStorage.getInstance();

        // Firebase Storage 초기화
        firebaseStorage = FirebaseStorage.getInstance();

        // Intent에서 feedId와 userId 가져오기
        Intent intent = getIntent();
        if (intent != null) {
            feedId = intent.getLongExtra("feedId", -1L);
            studentId = getIntent().getLongExtra("studentId", -1L);
        }

        if (feedId == -1L || studentId == -1L) {
            Toast.makeText(this, "잘못된 요청입니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 멤버 세부 정보 가져오기
        fetchMemberDetails(feedId, studentId);

        // 스크랩 버튼 클릭 리스너 추가
        scrapButton.setOnClickListener(v -> toggleScrap());
    }

    private void fetchMemberDetails(Long feedId, Long userId) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
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
        memberDepartmentTextView.setText(member.getSchool() + " " + member.getMajor());

        String category = member.getProjects().get(0).getCategory();
        memberRoleTextView.setText(category);

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

        // 스크랩 상태 초기화
        checkScrapStatus();
    }

    private void checkScrapStatus() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<Boolean> call = apiService.checkStudentScrapStatus(feedId, studentId);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    isScrapped = response.body(); // 서버에서 스크랩 상태 반환
                    updateScrapButton(); // 스크랩 버튼 UI 업데이트
                } else {
                    Toast.makeText(ItemDetailMemberRecruiterActivity.this, "스크랩 상태를 확인할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(ItemDetailMemberRecruiterActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toggleScrap() {
        scrapButton.setEnabled(false); // 요청 중 버튼 비활성화

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ScrapResponse> call = isScrapped
                ? apiService.unscrapStudent(feedId, studentId) // 스크랩 취소
                : apiService.scrapStudent(feedId, studentId);  // 스크랩

        call.enqueue(new Callback<ScrapResponse>() {
            @Override
            public void onResponse(Call<ScrapResponse> call, Response<ScrapResponse> response) {
                scrapButton.setEnabled(true); // 요청 완료 후 버튼 활성화

                if (response.isSuccessful() && response.body() != null) {
                    ScrapResponse scrapResponse = response.body();
                    isScrapped = !isScrapped; // 스크랩 상태 토글

                    // UI 업데이트
                    updateScrapButton();
                    Toast.makeText(ItemDetailMemberRecruiterActivity.this, scrapResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    handleError(response.code());
                }
            }

            @Override
            public void onFailure(Call<ScrapResponse> call, Throwable t) {
                scrapButton.setEnabled(true);
                Toast.makeText(ItemDetailMemberRecruiterActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleError(int errorCode) {
        switch (errorCode) {
            case 400:
                Toast.makeText(this, "이미 스크랩한 학생입니다.", Toast.LENGTH_SHORT).show();
                break;
            case 404:
                Toast.makeText(this, "학생을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "스크랩 상태를 변경하지 못했습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void updateScrapButton() {
        scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);
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