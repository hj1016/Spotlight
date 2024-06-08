package com.example.spotlight;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.LoginResponse;
import com.example.spotlight.network.Response.PortfolioResponse;
import com.example.spotlight.network.Response.UploadPortfolioResponse;
import com.example.spotlight.network.Util.FileUtils;
import com.example.spotlight.network.Util.TokenManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPagePortfolioActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton imageButton;
    private List<Uri> imageUris = new ArrayList<>();
    private ApiService apiService;
    private String id;
    private String role = "STUDENT";
    private String email;
    private GridLayout imageGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_portfolio);

        imageGrid = findViewById(R.id.image_grid);
        imageButton = findViewById(R.id.mypage_portfolio_selec_image_plus);
        apiService = ApiClient.getClientWithToken().create(ApiService.class);

        id = getIntent().getStringExtra("id");
        email = getIntent().getStringExtra("email");
        // 등록된 포트폴리오 이미지 적용
        int userId = TokenManager.getUserId();

        apiService.getPortfolio(userId).enqueue(new Callback<PortfolioResponse>() {
            @Override
            public void onResponse(Call<PortfolioResponse> call, Response<PortfolioResponse> response) {
                if (response.isSuccessful()) {
                    PortfolioResponse portfolioResponse = response.body();
                    List<String> imageUrls = portfolioResponse.getPortfolioImages();
                    if(imageUrls!=null && !imageUrls.isEmpty()) {
                        imageUrls.forEach(url -> {
                            Uri uri = Uri.parse(url);
                            imageUris.add(uri);
                        });
                    }
                    runOnUiThread(() -> updateGridLayout());
                } else {
                    Toast.makeText(MyPagePortfolioActivity.this, "등록된 포트폴리오가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<PortfolioResponse> call, Throwable t) {
                Toast.makeText(MyPagePortfolioActivity.this, "포트폴리오 불러오기 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImagePlusClicked(v);
            }
        });


    }
    private void updateGridLayout() {
        // 추가 버튼외 그리드 아이템 삭제
        int len = imageGrid.getChildCount();
        Log.d("GetChildCount",String.valueOf(len));
        if (len > 1) imageGrid.removeViews(1,len-1);

        // 이미지뷰 그리드 추가
        for (Uri imageUri : imageUris) {
            Log.d("imageUri-path", imageUri.getPath());
            ImageView imageView = new ImageView(this);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 280;
            layoutParams.height= 280;
            layoutParams.setMargins(30,60,30,60);

            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(this).load(imageUri).into(imageView);

            imageGrid.addView(imageView);
            Log.d("GridLayout", "Image added with URI: " + imageUri.toString());
        }

        // 레이아웃 무효화 및 재요청
        //imageGrid.invalidate();
        imageGrid.requestLayout();
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
            for (Uri uri : imageUris) {
                String filePath = FileUtils.getPath(this, uri);
                File imageFile = new File(filePath);

                RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(uri)), imageFile);
                MultipartBody.Part body = MultipartBody.Part.createFormData("portfolioImages", imageFile.getName(), requestFile);
                imageParts.add(body);
            }
            apiService.uploadPortfolio(imageParts).enqueue(new Callback<UploadPortfolioResponse>() {
                @Override
                public void onResponse(Call<UploadPortfolioResponse> call, Response<UploadPortfolioResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<String> imageUrls = response.body().getPortfolioList();
                        imageUris.clear();
                        imageUrls.forEach(url -> {
                            Uri uri = Uri.parse(url);
                            imageUris.add(uri);
                        });
                        runOnUiThread(() -> updateGridLayout());
                        Toast.makeText(MyPagePortfolioActivity.this, "업로드 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MyPagePortfolioActivity.this, MyPagePortfolioActivity.class);
                        startActivity(intent);
                    } else {
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
}
