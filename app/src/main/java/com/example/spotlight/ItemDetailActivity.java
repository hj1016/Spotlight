package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Arrays;
import java.util.List;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.ScrapCancelResponse;
import com.example.spotlight.network.Response.ScrapResponse;
import com.google.android.flexbox.FlexboxLayout;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailActivity extends AppCompatActivity {

    private ViewPager2 viewPagerImages;
    private ImageSliderAdapter adapter;

    private boolean isScrapped = false;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        sharedPreferences = getSharedPreferences("UserType", MODE_PRIVATE);

        // 이미지 URL로 ViewPager2 초기화
        List<String> images = Arrays.asList(
                "https://example.com/image1.jpg", // 실제 이미지 URL로 바꿔야 함
                "https://example.com/image2.jpg",
                "https://example.com/image3.jpg",
                "https://example.com/image4.jpg",
                "https://example.com/image5.jpg",
                "https://example.com/image6.jpg",
                "https://example.com/image7.jpg",
                "https://example.com/image8.jpg",
                "https://example.com/image9.jpg",
                "https://example.com/image10.jpg"
        );

        viewPagerImages = findViewById(R.id.viewPagerImages);
        adapter = new ImageSliderAdapter(images);
        viewPagerImages.setAdapter(adapter);

        List<String> hashtags = Arrays.asList("Art", "Exhibition"); // 해시태그 예시
        FlexboxLayout flexboxLayout = findViewById(R.id.flexbox_hashtags);
        addHashtags(hashtags, flexboxLayout);
    }

    public void addHashtags(List<String> hashtags, FlexboxLayout flexboxLayout) {
        for (String hashtag : hashtags) {
            TextView textView = new TextView(this);
            textView.setText("#" + hashtag);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setTextColor(getResources().getColor(R.color.white));
            textView.setBackground(getResources().getDrawable(R.drawable.hashtag_box));
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(20, 10, 20, 10);

            FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(10, 5, 10, 5);
            textView.setLayoutParams(layoutParams);

            flexboxLayout.addView(textView);
        }
    }


    public void onBackClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void toggleScrap(View view) {
        ImageView scrapButton = (ImageView) view;
        isScrapped = !isScrapped; // 스크랩 상태 토글

        // Intent로 feedId를 가져옴.
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("feedId")) {
            int feedId = intent.getIntExtra("feedId", -1);

            // 스크랩 또는 스크랩 취소 요청 보내기
            ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);

            if (isScrapped) {
                // 게시물 스크랩 요청
                Call<ScrapResponse> call = apiService.scrapFeed(feedId);
                call.enqueue(new Callback<ScrapResponse>() {
                    @Override
                    public void onResponse(Call<ScrapResponse> call, Response<ScrapResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            // 스크랩 성공
                            scrapButton.setImageResource(R.drawable.scrap_yes);
                            Toast.makeText(ItemDetailActivity.this, "게시물을 스크랩했습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            // 스크랩 실패
                            Toast.makeText(ItemDetailActivity.this, "게시물 스크랩에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ScrapResponse> call, Throwable t) {
                        // 네트워크 오류
                        Toast.makeText(ItemDetailActivity.this, "네트워크 오류", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                // 게시물 스크랩 취소 요청
                Call<ScrapCancelResponse> call = apiService.cancelScrapFeed(feedId);
                call.enqueue(new Callback<ScrapCancelResponse>() {
                    @Override
                    public void onResponse(Call<ScrapCancelResponse> call, Response<ScrapCancelResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            // 스크랩 취소 성공
                            scrapButton.setImageResource(R.drawable.scrap_no);
                            Toast.makeText(ItemDetailActivity.this, "게시물 스크랩을 취소했습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            // 스크랩 취소 실패
                            Toast.makeText(ItemDetailActivity.this, "게시물 스크랩 취소에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ScrapCancelResponse> call, Throwable t) {
                        // 네트워크 오류
                        Toast.makeText(ItemDetailActivity.this, "네트워크 오류", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public void onMemberClicked(View view) {
        String userType = sharedPreferences.getString("Type", "NORMAL");
        Intent intent;
        if (userType.equals("RECRUITER")) {
            intent = new Intent(this, ItemDetailMemberRecruiterActivity.class);
        } else {
            intent = new Intent(this, ItemDetailMemberGeneralActivity.class);
        }
        startActivity(intent);
    }
}
