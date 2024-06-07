package com.example.spotlight;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.UploadPortfolioResponse;
import com.example.spotlight.network.Util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPagePortfolioActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton imageButton;
    private List<ImageView> uploadedImageViews = new ArrayList<>();
    private ApiService apiService;
    private String id;
    private String role = "STUDENT";
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_portfolio);

        imageButton = findViewById(R.id.mypage_portfolio_selec_image_plus);
        apiService = ApiClient.getClientWithToken().create(ApiService.class);

        id = getIntent().getStringExtra("id");
        email = getIntent().getStringExtra("email");

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImagePlusClicked(v);
            }
        });
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onCompleteClicked(View view) {
        Intent intent = new Intent(this, MyPagePortfolioActivity.class);
        startActivity(intent);
    }

    public void onImagePlusClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                List<Uri> imageUris = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    imageUris.add(imageUri);
                }
                uploadImagesToServer(imageUris);
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                List<Uri> imageUris = new ArrayList<>();
                imageUris.add(imageUri);
                uploadImagesToServer(imageUris);
            }
        }
    }

    private void uploadImagesToServer(List<Uri> imageUris) {
        try {
            List<MultipartBody.Part> imageParts = new ArrayList<>();
            Log.d("upload", "upload00000");
            for (Uri uri : imageUris) {
                String filePath = FileUtils.getPath(this, uri);
                File imageFile = new File(filePath);
                Log.d("upload", "upload010101");

                RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(uri)), imageFile);
                MultipartBody.Part body = MultipartBody.Part.createFormData("portfolioImages", imageFile.getName(), requestFile);
                imageParts.add(body);
            }
            Log.d("upload", "upload____101010");
            apiService.uploadPortfolio(imageParts).enqueue(new Callback<UploadPortfolioResponse>() {
                @Override
                public void onResponse(Call<UploadPortfolioResponse> call, Response<UploadPortfolioResponse> response) {
                    Log.d("upload", "upload1111");
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("upload", "upload222success");
                        List<String> imageUrls = response.body().getPortfolioList();
                        Log.d("upload", "upload33333success");
                        Toast.makeText(MyPagePortfolioActivity.this, "업로드 성공", Toast.LENGTH_SHORT).show();
                        showUploadedImgs(imageUrls);
                        Log.d("upload", "upload4444success");
                    } else {
                        Log.d("upload", "upload5555failure");
                        Toast.makeText(MyPagePortfolioActivity.this, "업로드 실패: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UploadPortfolioResponse> call, Throwable t) {
                    Toast.makeText(MyPagePortfolioActivity.this, "업로드 에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "파일 처리 에러: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private RequestBody createPartFromString(String partString) {
        return RequestBody.create(MultipartBody.FORM, partString);
    }

    private void showUploadedImgs(List<String> imageUrls) {
        runOnUiThread(() -> {
            ConstraintLayout layout = findViewById(R.id.mypage_portfolio_layout);
            for (String imageUrl : imageUrls) {
                ImageView uploadedImageView = new ImageView(this);
                uploadedImageView.setVisibility(View.VISIBLE);
                Glide.with(this).load(imageUrl).into(uploadedImageView);
                layout.addView(uploadedImageView);
                uploadedImageViews.add(uploadedImageView);
            }
        });
    }
}
