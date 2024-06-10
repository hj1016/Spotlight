package com.example.spotlight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.ScrapCancelResponse;
import com.example.spotlight.network.Response.ScrapResponse;
import com.example.spotlight.network.Service.FeedService;
import com.google.android.flexbox.FlexboxLayout;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailActivity extends AppCompatActivity {

    private ViewPager2 viewPagerImages;
    private ImageSliderAdapter adapter;
    private boolean isScrapped = false;
    private SharedPreferences sharedPreferences;
    private TextView scrapCountTextView;
    private ImageView scrapButton;
    private int scrapCount;
    private ImageView teamImage;
    private TextView title, category, content, viewCountTextView1, viewCountTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        sharedPreferences = getSharedPreferences("UserType", MODE_PRIVATE);

        // Initialize views
        teamImage = findViewById(R.id.item_detail_team_image);
        title = findViewById(R.id.item_detail_title);
        category = findViewById(R.id.item_detail_category);
        content = findViewById(R.id.item_detail_content);
        viewCountTextView1 = findViewById(R.id.item_detail_general_view);
        viewCountTextView2 = findViewById(R.id.item_detail_recruiter_view);

        // Get intent data
        Intent intent = getIntent();
        int feedId = intent.getIntExtra("feedId", -1);

        if (intent != null && intent.hasExtra("post")) {
            Post post = (Post) intent.getSerializableExtra("post");
            if (post != null) {
                // Load data into views
                Glide.with(this).load(post.getTeamImageUrl()).into(teamImage);
                title.setText(post.getTitle());
                category.setText(post.getCategory());
                content.setText(post.getContent());
                scrapCountTextView.setText(String.valueOf(post.getScrap()));
                isScrapped = post.isScrapped();
                scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);
                scrapCount = post.getScrap();

                // Load hashtags
                FlexboxLayout flexboxLayout = findViewById(R.id.flexbox_hashtags);
                addHashtags(post.getHashtags(), flexboxLayout);
            }
        }

        List<Integer> images = Arrays.asList(
                R.drawable.image_ex1,
                R.drawable.image_ex1,
                R.drawable.image_ex1,
                R.drawable.image_ex1,
                R.drawable.image_ex1,
                R.drawable.image_ex1,
                R.drawable.image_ex1,
                R.drawable.image_ex1,
                R.drawable.image_ex1,
                R.drawable.image_ex1
        );

        viewPagerImages = findViewById(R.id.viewPagerImages);
        adapter = new ImageSliderAdapter(images);
        viewPagerImages.setAdapter(adapter);

        List<String> hashtags = Arrays.asList("Art", "Exhibition");
        FlexboxLayout flexboxLayout = findViewById(R.id.flexbox_hashtags);
        addHashtags(hashtags, flexboxLayout);

        scrapCountTextView = findViewById(R.id.item_scrap);
        scrapButton = findViewById(R.id.item_scrap_no);

        if (intent != null) {
            isScrapped = intent.getBooleanExtra("isScrapped", false);
            scrapCount = intent.getIntExtra("scrapCount", 0);

            scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);
            scrapCountTextView.setText(String.valueOf(scrapCount));
        }

        // FeedService 인스턴스 생성
        FeedService feedService = new FeedService(ApiClient.getClientWithToken().create(ApiService.class));

        // 일반 사용자인 경우
        feedService.updateHits(feedId, findViewById(R.id.item_detail_general_view), null);

        // 리크루터인 경우
        feedService.updateHits(feedId, null, findViewById(R.id.item_detail_recruiter_view));

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

            textView.setOnClickListener(this::onHashtagClicked); // Set click listener

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
        isScrapped = !isScrapped; // 스크랩 상태 토글

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("feedId")) {
            int feedId = intent.getIntExtra("feedId", -1);

            ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);

            if (isScrapped) {
                Call<ScrapResponse> call = apiService.scrapFeed(feedId);
                call.enqueue(new Callback<ScrapResponse>() {
                    @Override
                    public void onResponse(Call<ScrapResponse> call, Response<ScrapResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            scrapCount++;
                            scrapButton.setImageResource(R.drawable.scrap_yes);
                            scrapCountTextView.setText(String.valueOf(scrapCount));
                            Toast.makeText(ItemDetailActivity.this, "게시물을 스크랩했습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            isScrapped = false; // 스크랩 상태 롤백
                            Toast.makeText(ItemDetailActivity.this, "게시물 스크랩에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ScrapResponse> call, Throwable t) {
                        isScrapped = false; // 스크랩 상태 롤백
                        Toast.makeText(ItemDetailActivity.this, "네트워크 오류", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Call<ScrapCancelResponse> call = apiService.cancelScrapFeed(feedId);
                call.enqueue(new Callback<ScrapCancelResponse>() {
                    @Override
                    public void onResponse(Call<ScrapCancelResponse> call, Response<ScrapCancelResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            scrapCount--;
                            scrapButton.setImageResource(R.drawable.scrap_no);
                            scrapCountTextView.setText(String.valueOf(scrapCount));
                            Toast.makeText(ItemDetailActivity.this, "게시물 스크랩을 취소했습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            isScrapped = true; // 스크랩 상태 롤백
                            Toast.makeText(ItemDetailActivity.this, "게시물 스크랩 취소에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ScrapCancelResponse> call, Throwable t) {
                        isScrapped = true; // 스크랩 상태 롤백
                        Toast.makeText(ItemDetailActivity.this, "네트워크 오류", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public void onHashtagClicked(View view) {
        TextView textView = (TextView) view;
        String hashtag = textView.getText().toString().substring(1); // Remove the '#' symbol
        Intent intent = new Intent(ItemDetailActivity.this, SearchResultActivity.class);
        intent.putExtra("hashtag", hashtag);
        startActivity(intent);
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