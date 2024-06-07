package com.example.spotlight;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class NewPostingActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_SINGLE = 1;
    private static final int PICK_IMAGE_PLUS = 2;
    private Spinner bigCategorySpinner, smallCategorySpinner;
    private ArrayAdapter<CharSequence> smallCategoryAdapter;
    private ImageView dynamicImage;
    private EditText dynamicText;
    private ImageView[] imageViews = new ImageView[10];
    private ImageView imagePlusButton, imageSelectPlusButton;
    private RecyclerView recyclerView;
    private InviteMemberAdapter adapter;
    private List<Member> memberList;
    private List<Uri> imageUris = new ArrayList<>();
    private FirebaseStorage firebaseStorage;
    private String imageUrl = "";
    private String exhibitionLocation = "";
    private String exhibitionSchedule = "";
    private String exhibitionTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_posting);

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

        exhibitionLocation = "";
        exhibitionSchedule = "";
        exhibitionTime = "";

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

        imagePlusButton.setOnClickListener(view -> pickImage(PICK_IMAGE_PLUS));
        imageSelectPlusButton.setOnClickListener(view -> pickImage(PICK_IMAGE_SINGLE));

        // 전시 정보를 받아오는 부분
        Intent intent = getIntent();
        if (intent != null) {
            exhibitionLocation = intent.getStringExtra("location");
            exhibitionSchedule = intent.getStringExtra("schedule");
            exhibitionTime = intent.getStringExtra("time");

            // 받아온 전시 정보를 TextView에 설정함.
            updateExhibitionTextViews();
        }
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
        // memberList.add(new Member(R.drawable.member_image, "김이름"));
        // memberList.add(new Member(R.drawable.member_image, "이이름"));
        // memberList.add(new Member(R.drawable.member_image, "박이름"));

        adapter = new InviteMemberAdapter(this, memberList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
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
        if (resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri imageUri = data.getData();
                if (imageUris.size() < 10) {
                    imageUris.add(imageUri);
                    uploadImage(imageUri, imageUris.size() - 1);
                } else {
                    Toast.makeText(this, "최대 10장까지 이미지를 선택할 수 있습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void uploadImage(Uri imageUri, int index) {
        StorageReference storageReference = firebaseStorage.getReference().child("images/" + imageUri.getLastPathSegment());
        storageReference.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                    imageViews[index].setImageURI(imageUri);
                    Log.d("Image URL", uri.toString());
                    // 이미지 URL을 저장하고 활용하는 코드 추가
                    imageUrl = uri.toString();
                }))
                .addOnFailureListener(e -> Toast.makeText(NewPostingActivity.this, "이미지 업로드에 실패했습니다. : " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void updateExhibitionTextViews() {
        // 전시 정보를 받아온 경우에만 TextView를 업데이트함.
        if (exhibitionLocation != null && exhibitionSchedule != null && exhibitionTime != null &&
                !exhibitionLocation.isEmpty() && !exhibitionSchedule.isEmpty() && !exhibitionTime.isEmpty()) {
            TextView exhibitionLocationTextView = findViewById(R.id.exhibition_location);
            TextView exhibitionScheduleTextView = findViewById(R.id.exhibition_schedule);
            TextView exhibitionTimeTextView = findViewById(R.id.exhibition_time);
            TextView addNewPostingExhibitionTextView = findViewById(R.id.add_new_posting_exhibition_text);

            exhibitionLocationTextView.setText(exhibitionLocation);
            exhibitionScheduleTextView.setText(exhibitionSchedule);
            exhibitionTimeTextView.setText(exhibitionTime);
            addNewPostingExhibitionTextView.setText("");
        }
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
        startActivity(intent);
    }
}