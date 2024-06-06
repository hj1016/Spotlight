package com.example.spotlight;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class NewPostingActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST_1 = 1;
    private static final int PICK_IMAGE_REQUEST_2 = 2;
    private Uri imageUri;
    private ArrayList<Uri> imageUris = new ArrayList<>();
    private Spinner bigCategorySpinner, smallCategorySpinner;
    private ArrayAdapter<CharSequence> smallCategoryAdapter;
    private ImageView dynamicImage;
    private EditText dynamicText;

    private ImageView imageView1;
    private ImageView imageView2;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_posting);

        bigCategorySpinner = findViewById(R.id.big_category_spinner);
        smallCategorySpinner = findViewById(R.id.small_category_spinner);
        dynamicImage = findViewById(R.id.new_posting_description_box);
        dynamicText = findViewById(R.id.new_posting_description_text);

        imageView1 = findViewById(R.id.new_posting_image_plus);
        imageView2 = findViewById(R.id.new_posting_selec_image_plus);
        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        setupSpinners();
        setupDynamicImage();

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

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser(PICK_IMAGE_REQUEST_1);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser(PICK_IMAGE_REQUEST_2);
            }
        });
    }

    private void openFileChooser(int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // 다중 선택 가능
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == PICK_IMAGE_REQUEST_1) {
                // 이미지가 하나만 선택되었을 때
                imageUri = data.getData();
                imageView1.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
            } else if (requestCode == PICK_IMAGE_REQUEST_2) {
                // 다중 이미지 선택 시
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        imageUris.add(imageUri);
                        // 여기에서 imageUris에 대한 UI 업데이트도 수행할 수 있음.
                        uploadImageToFirebase(imageUri);
                    }
                }
            }
        }
    }


    private void uploadImageToFirebase(Uri imageUri) {
        if (imageUri != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(NewPostingActivity.this, "Upload successful", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(NewPostingActivity.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
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