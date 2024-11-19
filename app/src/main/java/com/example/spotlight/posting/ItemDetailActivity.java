package com.example.spotlight.posting;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.example.spotlight.common.ImageSliderAdapter;
import com.example.spotlight.main.MainActivity;
import com.example.spotlight.posting.Post;
import com.google.android.flexbox.FlexboxLayout;

import java.util.Arrays;
import java.util.List;

public class ItemDetailActivity extends AppCompatActivity {

    private ViewPager2 viewPagerImages;
    private ImageSliderAdapter adapter;
    private boolean isScrapped = false;
    private ImageView scrapButton;
    private ImageView teamImage;
    private TextView title, category, content, scrap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        // Initialize views
        teamImage = findViewById(R.id.item_detail_team_image);
        title = findViewById(R.id.item_detail_title);
        category = findViewById(R.id.item_detail_category);
        content = findViewById(R.id.item_detail_content);
        scrap = findViewById(R.id.item_scrap);

        // Get intent data
        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("post")) {
            Post post = (Post) intent.getSerializableExtra("post");
            if (post != null) {
                // Load data into views
                Glide.with(this).load(post.getTeamImageUrl()).into(teamImage);
                title.setText(post.getTitle());
                category.setText(post.getCategory());
                content.setText(post.getContent());
                scrap.setText(post.getScrap());
                isScrapped = post.isScrapped();
                scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);

                // Load hashtags
                FlexboxLayout flexboxLayout = findViewById(R.id.flexbox_hashtags);
                addHashtags(post.getHashtags(), flexboxLayout);
            }
        }

        List<Integer> images = Arrays.asList(
                R.drawable.image_ex1,
                R.drawable.photography2,
                R.drawable.photography3,
                R.drawable.photography4
        );

        viewPagerImages = findViewById(R.id.viewPagerImages);
        adapter = new ImageSliderAdapter(images);
        viewPagerImages.setAdapter(adapter);

        List<String> hashtags = Arrays.asList("A.E.S", "Photo", "Photography");
        FlexboxLayout flexboxLayout = findViewById(R.id.flexbox_hashtags);
        addHashtags(hashtags, flexboxLayout);

        scrapButton = findViewById(R.id.item_scrap_no);

        if (intent != null) {
            isScrapped = intent.getBooleanExtra("isScrapped", false);
            scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);
        }
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

            //textView.setOnClickListener(this::onHashtagClicked); // Set click listener

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
        toggleScrap();
    }


    public void toggleScrap() {
        // 현재 스크랩 상태를 토글합니다.
        isScrapped = !isScrapped;
        scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);

        // 스크랩 상태에 따라서 scrap 텍스트뷰의 값을 증가 또는 감소시킵니다.
        int currentScrapCount = Integer.parseInt(scrap.getText().toString());
        scrap.setText(String.valueOf(isScrapped ? currentScrapCount + 1 : currentScrapCount - 1));

        /*
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

         */
    }

    /*
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

     */
}