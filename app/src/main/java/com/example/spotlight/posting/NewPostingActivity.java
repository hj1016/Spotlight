package com.example.spotlight.posting;
/*
import android.Manifest;
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
import com.example.spotlight.network.DTO.ProjectDTO;
import com.example.spotlight.network.Request.FeedRequest;
import com.example.spotlight.network.Response.FeedResponse;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private TextView exhibitionInfoTextView;
    private boolean isExhibitionInfoSaved = false;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_posting);

        apiService = ApiClient.getClientWithToken().create(ApiService.class);

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
                // Set the height of the ImageView based on text length
                params.height = 200 + charSequence.length() * 5; // Basic calculation, adjust as needed
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
        updateSmallCategories(0); // Initialize with the first big category
    }

    private void setupDynamicImage() {
        // You can set up more configurations here if needed
    }

    private void setupRecyclerView() {
        memberList = new ArrayList<>();

        memberAdapter = new MemberAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(memberAdapter);
    }

    private void updateSmallCategories(int position) {
        int arrayId = R.array.small_categories_humanities; // Default value

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
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            if (requestCode == PICK_IMAGE_SINGLE) {
                Glide.with(this).load(selectedImageUri).into(imageViews[0]);
                imageUris.add(selectedImageUri);
            } else if (requestCode == PICK_IMAGE_PLUS) {
                if (imageUris.size() < imageViews.length) {
                    imageUris.add(selectedImageUri);
                    Glide.with(this).load(selectedImageUri).into(imageViews[imageUris.size() - 1]);
                } else {
                    Toast.makeText(this, "더 이상 이미지를 추가할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (requestCode == REQUEST_CODE_MEMBER_INVITE) {
            // 멤버가 추가되었을 때의 처리
            if (data != null) {
                String memberId = data.getStringExtra("memberId");
                String role = data.getStringExtra("role");
                if (memberId != null && !memberId.isEmpty()) {
                    // Add the new member to the list and update the RecyclerView
                    memberList.add(new Member(R.drawable.member_image, memberId, role));
                    memberAdapter.notifyDataSetChanged();
                }
            }
        }

        if (requestCode == REQUEST_CODE_EXHIBITION_INFO) {
            if (resultCode == RESULT_OK) {
                // 전시 정보가 성공적으로 저장된 경우 TextView 업데이트
                String exhibitionInfo = "전시 정보 입력 완료";
                exhibitionInfoTextView.setText(exhibitionInfo);
                isExhibitionInfoSaved = true;
            } else if (resultCode == RESULT_CANCELED) {
                // 전시 정보 저장이 실패한 경우
                exhibitionInfoTextView.setText("전시 정보 입력 실패");
                isExhibitionInfoSaved = false;
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
        StorageReference storageReference = firebaseStorage.getReference().child("images/" + imageUri.getLastPathSegment());
        storageReference.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        imageViews[index].setImageURI(imageUri);
                        Log.d("Image URL", uri.toString());
                        imageUrl = uri.toString();
                    }).addOnFailureListener(e -> {
                        Toast.makeText(NewPostingActivity.this, "이미지 업로드에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        Log.e("NewPostingActivity", "이미지 다운로드 URL 가져오기 실패", e);
                    });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(NewPostingActivity.this, "이미지 업로드에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    Log.e("NewPostingActivity", "이미지 업로드 실패", e);
                });
    }

    private void createTeam(Integer projectId, String title, String content, String bigCategory, String smallCategory, String hashtags) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setProjectId(projectId);

        Call<TeamDTO> call = apiService.createTeam(teamDTO, projectId);
        call.enqueue(new Callback<TeamDTO>() {
            @Override
            public void onResponse(Call<TeamDTO> call, Response<TeamDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Integer teamId = response.body().getTeamId();

                    // 게시물 작성 요청
                    createFeed(title, content, bigCategory, smallCategory, hashtags, projectId, teamId);
                } else {
                    showToast("팀 생성에 실패했습니다.");
                }
            }

            @Override
            public void onFailure(Call<TeamDTO> call, Throwable t) {
                showToast("팀 생성에 실패했습니다. " + t.getMessage());
            }
        });
    }

    private void createFeed(String title, String content, String bigCategory, String smallCategory, String hashtags, Integer projectId, Integer teamId) {
        FeedRequest feedRequest = new FeedRequest();
        feedRequest.setTitle(title);
        feedRequest.setContent(content);
        feedRequest.setScrap(0);  // 기본값

        FeedRequest.Category category = new FeedRequest.Category();
        category.setMain(bigCategory);
        category.setSub(smallCategory);
        feedRequest.setCategory(category);

        List<String> hashtagList = Arrays.asList(hashtags.split(" "));
        feedRequest.setHashtag(hashtagList);

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(projectId);
        feedRequest.setProjectId(projectDTO);

        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamId(teamId);
        feedRequest.setTeamId(teamDTO);

        Call<FeedResponse> call = apiService.createFeed(feedRequest);
        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FeedResponse feedDTO = response.body();
                    Integer feedId = feedDTO.getFeedId();

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
            public void onFailure(Call<FeedResponse> call, Throwable t) {
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

        ProjectDTO project = new ProjectDTO();
        project.setProjectName(title);

        Call<ProjectDTO> projectCall = apiService.createProject(project);
        projectCall.enqueue(new Callback<ProjectDTO>() {
            @Override
            public void onResponse(Call<ProjectDTO> call, Response<ProjectDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Integer projectId = response.body().getProjectId();
                    createTeam(projectId, title, content, bigCategory, smallCategory, hashtags);
                } else {
                    showToast("프로젝트 생성에 실패했습니다.");
                }
            }

            @Override
            public void onFailure(Call<ProjectDTO> call, Throwable t) {
                showToast("프로젝트 생성에 실패했습니다. " + t.getMessage());
            }
        });
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

 */