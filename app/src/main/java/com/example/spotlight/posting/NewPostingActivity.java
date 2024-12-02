package com.example.spotlight.posting;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.DTO.FeedDTO;
import com.example.spotlight.network.DTO.ProjectDTO;
import com.example.spotlight.network.Request.FeedRequest;
import com.example.spotlight.network.Response.FeedResponse;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPostingActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_SINGLE = 1;
    private static final int PICK_IMAGE_PLUS = 2;
    private static final int REQUEST_CODE_MEMBER_INVITE = 3;
    private static final int REQUEST_CODE_EXHIBITION_INFO = 4;
    private static final int STORAGE_PERMISSION_CODE = 101;

    private Spinner bigCategorySpinner, smallCategorySpinner;
    private ArrayAdapter<CharSequence> smallCategoryAdapter;
    private ImageView dynamicImage;
    private EditText dynamicText;
    private ImageView[] imageViews = new ImageView[10];
    private ImageView imagePlusButton, imageSelectPlusButton;

    private RecyclerView recyclerView;
    private MemberAdapter memberAdapter;
    private List<Member> memberList;

    private List<Uri> imageUris = new ArrayList<>();
    private FirebaseStorage firebaseStorage;

    private List<Post> posts = new ArrayList<>();
    private PostDataManager postDataManager;
    private PostAdapter postAdapter;

    private String teamImageUrl = "";
    private String imageUrl = "";
    private String scrapImageUrl = "";

    private Long exhibitionId;
    private TextView exhibitionInfoTextView;
    private boolean isExhibitionInfoSaved = false;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_posting);

        apiService = ApiClient.getClient().create(ApiService.class);

        bigCategorySpinner = findViewById(R.id.big_category_spinner);
        smallCategorySpinner = findViewById(R.id.small_category_spinner);
        dynamicImage = findViewById(R.id.new_posting_description_box);
        dynamicText = findViewById(R.id.new_posting_description_text);

        imageViews[0] = findViewById(R.id.new_posting_image1);
        imageViews[1] = findViewById(R.id.new_posting_image2);
        imageViews[2] = findViewById(R.id.new_posting_image3);
        imageViews[3] = findViewById(R.id.new_posting_image4);
        imageViews[4] = findViewById(R.id.new_posting_image5);
        imageViews[5] = findViewById(R.id.new_posting_image6);
        imageViews[6] = findViewById(R.id.new_posting_image7);
        imageViews[7] = findViewById(R.id.new_posting_image8);
        imageViews[8] = findViewById(R.id.new_posting_image9);
        imageViews[9] = findViewById(R.id.new_posting_image10);

        imagePlusButton = findViewById(R.id.new_posting_image_plus);
        imageSelectPlusButton = findViewById(R.id.new_posting_selec_image_plus);
        recyclerView = findViewById(R.id.recyclerView_invite_member);
        exhibitionInfoTextView = findViewById(R.id.add_new_posting_exhibition_text);

        setupSpinners();
        setupDynamicImage();
        setupRecyclerView();

        dynamicText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) dynamicImage.getLayoutParams();
                params.height = 200 + charSequence.length() * 5;
                dynamicImage.setLayoutParams(params);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        firebaseStorage = FirebaseStorage.getInstance();
        postDataManager = new PostDataManager(this);

        // 기존 게시물 불러오기
        posts = postDataManager.loadPosts();

        // 기존 게시물 어댑터 설정
        postAdapter = new PostAdapter(this, posts);

        imagePlusButton.setOnClickListener(view -> {
            if (checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE)) {
                pickImage(PICK_IMAGE_PLUS);
            }
        });

        imageSelectPlusButton.setOnClickListener(view -> {
            if (checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE)) {
                pickImage(PICK_IMAGE_SINGLE);
            }
        });
    }

    private void setupSpinners() {
        ArrayAdapter<CharSequence> bigCategoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.big_categories, android.R.layout.simple_spinner_item);
        bigCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bigCategorySpinner.setAdapter(bigCategoryAdapter);
        bigCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSmallCategories(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        updateSmallCategories(0);
    }

    private void setupDynamicImage() {
        // You can set up more configurations here if needed
    }

    private void setupRecyclerView() {
        memberList = new ArrayList<>();

        memberAdapter = new MemberAdapter(new ArrayList<>(), new FeedDTO());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(memberAdapter);
    }

    private void updateSmallCategories(int position) {
        int arrayId = R.array.small_categories_humanities;

        switch (position) {
            case 0:
                arrayId = R.array.small_categories_humanities;
                break;
            case 1:
                arrayId = R.array.small_categories_social;
                break;
            case 2:
                arrayId = R.array.small_categories_education;
                break;
            case 3:
                arrayId = R.array.small_categories_nature;
                break;
            case 4:
                arrayId = R.array.small_categories_engineering;
                break;
            case 5:
                arrayId = R.array.small_categories_medical;
                break;
            case 6:
                arrayId = R.array.small_categories_entertainment;
                break;
        }

        smallCategoryAdapter = ArrayAdapter.createFromResource(this,
                arrayId, android.R.layout.simple_spinner_item);
        smallCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smallCategorySpinner.setAdapter(smallCategoryAdapter);
    }

    private void pickImage(int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_MEMBER_INVITE) {
                // 멤버 초대 결과 처리
                String name = data.getStringExtra("name");
                String role = data.getStringExtra("role");
                int defaultImageResource = R.drawable.member_image; // 기본 이미지 리소스

                if (name != null && role != null) {
                    memberList.add(new Member(defaultImageResource, name, role));
                    memberAdapter.notifyDataSetChanged();
                }

            } else if (requestCode == REQUEST_CODE_EXHIBITION_INFO) {
                // 전시 정보 결과 처리
                exhibitionId = data.getLongExtra("exhibitionId", -1L); // 전시 ID 가져오기
                if (exhibitionId != -1L) {
                    exhibitionInfoTextView.setText("전시 정보 입력 완료");
                    isExhibitionInfoSaved = true;
                } else {
                    exhibitionInfoTextView.setText("전시 정보 입력 실패");
                    isExhibitionInfoSaved = false;
                }
            }
        }
    }

    private boolean checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "스토리지 권한이 승인되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "스토리지 권한이 거부되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 이미지 업로드 메소드
    private void uploadImage(Uri imageUri, int index) {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + imageUri.getLastPathSegment();
        StorageReference storageReference = firebaseStorage.getReference().child("images/" + uniqueFileName);

        ProgressDialog progressDialog = new ProgressDialog(NewPostingActivity.this);
        progressDialog.setMessage("이미지 업로드 중...");
        progressDialog.show();

        storageReference.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        Glide.with(NewPostingActivity.this).load(uri).into(imageViews[index]);
                        Log.d("Image URL", uri.toString());
                        imageUrl = uri.toString();
                        progressDialog.dismiss(); // 업로드 완료 시 로딩 종료
                    }).addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(NewPostingActivity.this, "이미지 업로드에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        Log.e("NewPostingActivity", "이미지 다운로드 URL 가져오기 실패", e);
                    });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(NewPostingActivity.this, "이미지 업로드에 실패했습니다. 다시 시도하세요.", Toast.LENGTH_SHORT).show();
                    Log.e("NewPostingActivity", "이미지 업로드 실패", e);

                    // 재시도 버튼 표시
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewPostingActivity.this);
                    builder.setTitle("업로드 실패")
                            .setMessage("이미지 업로드에 실패했습니다. 다시 시도하시겠습니까?")
                            .setPositiveButton("다시 시도", (dialog, which) -> uploadImage(imageUri, index))
                            .setNegativeButton("취소", null)
                            .show();
                });

    }

    // 게시물 작성 메서드
    private void createFeed(String title, String description, Long mainCategoryId, Long subCategoryId,
                            String hashtags, String projectImage, List<String> additionalImages,
                            List<Long> teamMemberIds, Long exhibitionId) {
        // FeedRequest 객체 생성 및 데이터 설정
        FeedRequest feedRequest = new FeedRequest();
        feedRequest.setTitle(title);
        feedRequest.setDescription(description);
        feedRequest.setMainCategoryId(mainCategoryId);
        feedRequest.setSubCategoryId(subCategoryId);
        feedRequest.setProjectImage(projectImage);
        feedRequest.setAdditionalImages(additionalImages);

        // 해시태그를 공백으로 분리하여 Set으로 변환
        Set<String> hashtagSet = new HashSet<>(Arrays.asList(hashtags.split(" ")));
        feedRequest.setHashtags(hashtagSet);

        feedRequest.setTeamMemberIds(teamMemberIds);
        feedRequest.setExhibitionId(exhibitionId);

        // API 호출
        Call<FeedDTO> call = apiService.createFeed(feedRequest);
        call.enqueue(new Callback<FeedDTO>() {
            @Override
            public void onResponse(Call<FeedDTO> call, Response<FeedDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FeedDTO createdFeed = response.body();
                    Long feedId = createdFeed.getFeedId();

                    showToast("게시물이 성공적으로 작성되었습니다!");
                    Intent intent = new Intent(NewPostingActivity.this, ItemDetailActivity.class);
                    intent.putExtra("feedId", feedId);
                    startActivity(intent);
                    finish();
                } else {
                    showToast("게시물 작성에 실패했습니다.");
                }
            }

            @Override
            public void onFailure(Call<FeedDTO> call, Throwable t) {
                showToast("게시물 작성에 실패했습니다. " + t.getMessage());
            }
        });
    }

    public void onCompleteClicked(View view) {
        String title = ((EditText) findViewById(R.id.new_posting_project_text)).getText().toString();
        String content = ((EditText) findViewById(R.id.new_posting_description_text)).getText().toString();
        String bigCategory = bigCategorySpinner.getSelectedItem().toString();
        String smallCategory = smallCategorySpinner.getSelectedItem().toString();
        String hashtags = ((EditText) findViewById(R.id.new_posting_hashtag_text)).getText().toString();

        if (title.isEmpty() || content.isEmpty() || exhibitionId == null) {
            showToast("필수 정보를 모두 입력해주세요.");
            return;
        }

        // 추가된 멤버 ID 리스트
        List<Long> teamMemberIds = new ArrayList<>();
        for (Member member : memberList) {
            // 멤버 ID를 Long으로 변환 (데이터 예시 기반 처리 필요)
            teamMemberIds.add(Long.parseLong(member.getName()));
        }

        // 게시물 작성 메서드 호출
        createFeed(
                title,                              // String
                content,                            // String
                getMainCategoryId(bigCategory),     // Long
                getSubCategoryId(smallCategory),    // Long
                hashtags,                           // String
                teamImageUrl != null ? teamImageUrl : "", // String
                getAdditionalImages(),              // List<String>
                teamMemberIds != null ? teamMemberIds : new ArrayList<>(), // List<Long>
                exhibitionId                        // Long
        );

    }

    private Long getMainCategoryId(String bigCategory) {
        // bigCategory에 해당하는 category_id를 DB에서 조회
        switch (bigCategory) {
            case "인문":
                return 1L;
            case "사회":
                return 2L;
            case "교육":
                return 3L;
            case "자연":
                return 4L;
            case "공학":
                return 5L;
            case "의약":
                return 6L;
            case "예체능":
                return 7L;
            default:
                return null;
        }
    }

    private Long getSubCategoryId(String smallCategory) {
        // smallCategory에 해당하는 category_id를 DB에서 조회
        switch (smallCategory) {
            case "언어":
                return 8L;
            case "문헌정보":
                return 9L;
            case "심리학":
                return 10L;
            case "역사":
                return 11L;
            case "문학":
                return 12L;
            case "종교":
                return 13L;
            case "철학":
                return 14L;
            case "경영":
                return 15L;
            case "경제":
                return 16L;
            case "광고/홍보":
                return 17L;
            case "행정":
                return 18L;
            case "국제학":
                return 19L;
            case "사회복지":
                return 20L;
            case "무역":
                return 21L;
            case "법학":
                return 22L;
            case "보건행정":
                return 23L;
            case "세무/회계":
                return 24L;
            case "신문방송":
                return 25L;
            case "정치외교":
                return 26L;
            case "지리학":
                return 27L;
            case "관광":
                return 28L;
            case "마케팅":
                return 29L;
            case "농업":
                return 30L;
            case "물리":
                return 31L;
            case "산림/원예":
                return 32L;
            case "생명과학":
                return 33L;
            case "수의학":
                return 34L;
            case "수학":
                return 35L;
            case "식품영양":
                return 36L;
            case "지구과학":
                return 37L;
            case "천문":
                return 38L;
            case "통계":
                return 39L;
            case "화학":
                return 40L;
            case "기계":
                return 41L;
            case "건축":
                return 42L;
            case "도시":
                return 43L;
            case "반도체":
                return 44L;
            case "산업":
                return 45L;
            case "신소재":
                return 46L;
            case "생명":
                return 47L;
            case "소프트웨어":
                return 48L;
            case "에너지":
                return 49L;
            case "재료/금속":
                return 50L;
            case "전기/전자":
                return 51L;
            case "정보통신":
                return 52L;
            case "정보보안":
                return 53L;
            case "컴퓨터":
                return 54L;
            case "토목":
                return 55L;
            case "환경":
                return 56L;
            case "화학공학":
                return 57L;
            case "보건":
                return 58L;
            case "약학":
                return 59L;
            case "의료공학":
                return 60L;
            case "의학":
                return 61L;
            case "임상병리":
                return 62L;
            case "치의학":
                return 63L;
            case "한의학":
                return 64L;
            case "공예":
                return 65L;
            case "무용":
                return 66L;
            case "애니메이션":
                return 67L;
            case "순수미술":
                return 68L;
            case "연극영화":
                return 69L;
            case "뷰티":
                return 70L;
            case "사진/영상":
                return 71L;
            case "산업 디자인":
                return 72L;
            case "시각 디자인":
                return 73L;
            case "실내 디자인":
                return 74L;
            case "패션 디자인":
                return 75L;
            case "실용음악":
                return 76L;
            case "음악":
                return 77L;
            case "조형":
                return 78L;
            case "체육":
                return 79L;
            default:
                return null;
        }
    }

    private List<String> getAdditionalImages() {
        List<String> imageUrls = new ArrayList<>();
        for (Uri uri : imageUris) {
            if (uri != null) {
                imageUrls.add(uri.toString());
            }
        }
        return imageUrls.isEmpty() ? new ArrayList<>() : imageUrls;
    }


    private void showToast(String message) {
        Toast.makeText(NewPostingActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onMemberPlusClicked(View view) {
        Intent intent = new Intent(this, NewPostingMemberActivity.class);
        startActivity(intent);
    }

    public void onExhibitionPlusClicked(View view) {
        Intent intent = new Intent(this, NewPostingExhibitionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_EXHIBITION_INFO);
    }
}