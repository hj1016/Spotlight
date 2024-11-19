package com.example.spotlight.authentication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.example.spotlight.main.MainActivity;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.CertificateResponse;
import com.example.spotlight.network.Util.FileUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecruiterStep2Activity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private ImageView uploadedImageView;
    private ApiService apiService;
    private String id;
    private String role = "RECRUITER";
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruiter_step2);

        imageView = findViewById(R.id.recruiter_step2_image_plus);
        uploadedImageView = new ImageView(this);
        apiService = ApiClient.getClientWithToken().create(ApiService.class);

        id = getIntent().getStringExtra("id");
        email = getIntent().getStringExtra("email");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImagePlusClicked(v);
            }
        });
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, SignupStep5Activity.class);
        startActivity(intent);
    }

    public void onContinueRecruiterSignup2Clicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onImagePlusClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                imageView.setImageBitmap(bitmap);
                uploadImageToServer(selectedImageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToServer(Uri imageUri) {
        try {
            String filePath = FileUtils.getPath(this, imageUri);
            File imageFile = new File(filePath);

            RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(imageUri)), imageFile);
            MultipartBody.Part body = MultipartBody.Part.createFormData("certificate", imageFile.getName(), requestFile);

            apiService.uploadCertificate(body).enqueue(new Callback<CertificateResponse>() {
                @Override
                public void onResponse(Call<CertificateResponse> call, Response<CertificateResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String imageUrl = response.body().getCertificate();
                        Toast.makeText(RecruiterStep2Activity.this, "업로드 성공", Toast.LENGTH_SHORT).show();
                        showUploadedImg(imageUrl);
                    } else {
                        Toast.makeText(RecruiterStep2Activity.this, "업로드 실패: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CertificateResponse> call, Throwable t) {
                    Toast.makeText(RecruiterStep2Activity.this, "업로드 에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "파일 처리 에러: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showUploadedImg(String imageUrl) {
        runOnUiThread(() -> {
            uploadedImageView.setVisibility(View.VISIBLE);
            Glide.with(this).load(imageUrl).into(uploadedImageView);
            ConstraintLayout layout = findViewById(R.id.recruiter_step2_layout);
            layout.addView(uploadedImageView);
        });
    }
}
