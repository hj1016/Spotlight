package com.example.spotlight.posting;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.example.spotlight.common.ImageSliderAdapter;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.FeedResponse;
import com.example.spotlight.search.SearchResultActivity;
import com.google.android.flexbox.FlexboxLayout;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailActivity extends AppCompatActivity {

    private ViewPager2 viewPagerImages;
    private ImageSliderAdapter adapter;
    private boolean isScrapped = false;
    private ImageView scrapButton;
    private ImageView teamImage;
    private TextView title, category, content, scrap, viewCount, viewCountRecruiter;
    private FlexboxLayout flexboxLayout;
    private Long feedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        // View 초기화
        initializeViews();

        // Intent로 전달된 feedId 가져오기
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("feedId")) {
            feedId = intent.getLongExtra("feedId", -1);
            if (feedId != -1) {
                loadFeedDetail(feedId); // 게시물 세부 정보 로드
            }
        }
    }

    private void initializeViews() {
        // XML에서 View 가져오기
        teamImage = findViewById(R.id.item_detail_team_image);
        title = findViewById(R.id.item_detail_title);
        category = findViewById(R.id.item_detail_category);
        content = findViewById(R.id.item_detail_content);
        scrap = findViewById(R.id.item_scrap);
        viewCount = findViewById(R.id.item_detail_general_view);
        viewCountRecruiter = findViewById(R.id.item_detail_recruiter_view);
        flexboxLayout = findViewById(R.id.flexbox_hashtags);
        scrapButton = findViewById(R.id.item_scrap_no);

        // 이미지 슬라이더 초기화
        viewPagerImages = findViewById(R.id.viewPagerImages);
        adapter = new ImageSliderAdapter(Arrays.asList(
                R.drawable.image_ex1, R.drawable.photography2, R.drawable.photography3
        ));
        viewPagerImages.setAdapter(adapter);
    }

    private void loadFeedDetail(Long feedId) {
        // API 호출하여 게시물 세부 정보 가져오기
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<FeedResponse> call = apiService.getFeed(feedId);

        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FeedResponse feed = response.body();
                    populateFeedData(feed);
                } else {
                    Toast.makeText(ItemDetailActivity.this, "게시물 정보를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                Toast.makeText(ItemDetailActivity.this, "네트워크 오류 발생", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateFeedData(FeedResponse feedResponse) {
        FeedResponse.Post post = feedResponse.getPost();

        if (post == null) {
            Toast.makeText(this, "게시물 데이터를 로드할 수 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 제목, 내용, 카테고리 설정
        title.setText(post.getTitle());
        content.setText(post.getContent());
        if (post.getCategory() != null) {
            category.setText(post.getCategory().getName());
        }

        // 조회수 설정
        viewCount.setText(String.valueOf(post.getHitsUser()));
        viewCountRecruiter.setText(String.valueOf(post.getHitsRecruiter()));

        // 스크랩 설정
        scrap.setText(String.valueOf(post.getScrap()));
        isScrapped = post.getScrap() > 0; // 기본적으로 스크랩 상태로 설정
        scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);

        // 팀 이미지 설정
        Glide.with(this).load(post.getThumbnailImage()).into(teamImage);

        // 이미지 슬라이더 설정
        if (post.getFeedImages() != null && !post.getFeedImages().isEmpty()) {
            setupImageSlider(post.getFeedImages());
        }

        // 해시태그 설정
        if (post.getHashtags() != null && !post.getHashtags().isEmpty()) {
            addHashtags(post.getHashtags());
        }

        // 전시 정보 설정
        if (post.getExhibition() != null) {
            FeedResponse.Post.Exhibition exhibition = post.getExhibition();
            TextView exhibitionLocation = findViewById(R.id.item_detail_exhibition_location);
            TextView exhibitionDate = findViewById(R.id.item_detail_exhibition_date);
            TextView exhibitionTime = findViewById(R.id.item_detail_exhibition_time);

            exhibitionLocation.setText(exhibition.getLocation());
            exhibitionDate.setText(exhibition.getSchedule());
            exhibitionTime.setText(exhibition.getTime());
        }
    }

    // 이미지 슬라이더 직접 설정
    private void setupImageSlider(List<String> feedImages) {
        if (feedImages == null || feedImages.isEmpty()) return;

        viewPagerImages.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ImageView imageView = new ImageView(parent.getContext());
                imageView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                ));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return new RecyclerView.ViewHolder(imageView) {};
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                loadImage(feedImages.get(position), (ImageView) holder.itemView);
            }

            @Override
            public int getItemCount() {
                return feedImages.size();
            }
        });
    }

    private void loadImage(String imageUrl, ImageView imageView) {
        Glide.with(this)
                .load(imageUrl)
                //.placeholder(R.drawable.placeholder_image)
                //.error(R.drawable.error_image)
                .into(imageView);
    }

    private void addHashtags(Set<FeedResponse.Post.Hashtag> hashtags) {
        for (FeedResponse.Post.Hashtag hashtag : hashtags) {
            TextView textView = new TextView(this);
            textView.setText("#" + hashtag.getHashtag());
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

            textView.setOnClickListener(this::onHashtagClicked);

            flexboxLayout.addView(textView);
        }
    }

    public void onBackClicked(View view) {
        // 뒤로가기 버튼 동작
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

    public void onHashtagClicked(View view) {
        TextView textView = (TextView) view;
        String hashtag = textView.getText().toString().substring(1); // Remove the '#' symbol
        Intent intent = new Intent(ItemDetailActivity.this, SearchResultActivity.class);
        intent.putExtra("hashtag", hashtag);
        startActivity(intent);
    }

    public void onMemberClicked(View view) {
        /*
        String userType = sharedPreferences.getString("Type", "NORMAL");
        Intent intent;
        if (userType.equals("RECRUITER")) {
            intent = new Intent(this, ItemDetailMemberRecruiterActivity.class);
        } else {
            intent = new Intent(this, ItemDetailMemberGeneralActivity.class);
        }
        startActivity(intent);

         */
    }
}