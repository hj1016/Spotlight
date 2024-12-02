package com.example.spotlight.posting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.example.spotlight.common.ImageSliderAdapter;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.DTO.ProjectRoleDTO;
import com.example.spotlight.network.Response.FeedResponse;
import com.example.spotlight.network.Response.ScrapResponse;
import com.example.spotlight.network.Util.TokenManager;
import com.example.spotlight.network.DTO.FeedDTO;
import com.example.spotlight.search.SearchResultActivity;
import com.google.android.flexbox.FlexboxLayout;

import java.io.Serializable;

import java.util.ArrayList;
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
    private RecyclerView recyclerView;
    private MemberAdapter memberAdapter;
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
            Log.d("ItemDetailActivity", "Feed ID received: " + feedId);

            if (feedId == -1) {
                Toast.makeText(this, "잘못된 요청입니다.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                loadFeedDetail(feedId); // 게시물 세부 정보 로드
            }

        } else {
            Toast.makeText(this, "feedId가 전달되지 않았습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

        // 스크랩 버튼 클릭 리스너 설정
        scrapButton.setOnClickListener(v -> toggleScrap());
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

        recyclerView = findViewById(R.id.team_member_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 이미지 슬라이더 초기화
        viewPagerImages = findViewById(R.id.viewPagerImages);
        adapter = new ImageSliderAdapter(Arrays.asList(
                R.drawable.image_ex1, R.drawable.photography2, R.drawable.photography3
        ));
        viewPagerImages.setAdapter(adapter);
    }

    private void loadFeedDetail(Long feedId) {
        // API 호출하여 게시물 세부 정보 가져오기
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<FeedDTO> call = apiService.getFeed(feedId);

        call.enqueue(new Callback<FeedDTO>() {
            @Override
            public void onResponse(Call<FeedDTO> call, Response<FeedDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FeedDTO feed = response.body();
                    populateFeedData(feed);
                } else {
                    Toast.makeText(ItemDetailActivity.this, "게시물 정보를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedDTO> call, Throwable t) {
                String errorMessage = (t instanceof java.net.SocketTimeoutException)
                        ? "서버 응답 시간이 초과되었습니다. 다시 시도해주세요."
                        : "네트워크 오류가 발생했습니다. 연결 상태를 확인하세요.";
                Toast.makeText(ItemDetailActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateFeedData(FeedDTO feed) {
        if (feed == null) {
            Toast.makeText(this, "게시물 데이터를 로드할 수 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 제목, 내용, 카테고리 설정
        title.setText(feed.getTitle());
        content.setText(feed.getContent());
        if (feed.getCategory() != null) {
            category.setText(feed.getCategory().getName());
        }

        // 조회수 설정
        viewCount.setText(String.valueOf(feed.getHitsUser()));
        viewCountRecruiter.setText(String.valueOf(feed.getHitsRecruiter()));

        // 스크랩 설정
        scrap.setText(String.valueOf(feed.getScrap()));
        isScrapped = feed.getScrap() > 0; // 기본적으로 스크랩 상태로 설정
        scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);

        // 팀 이미지 설정
        Glide.with(this).load(feed.getThumbnailImage()).into(teamImage);

        // 이미지 슬라이더 설정
        if (feed.getFeedImages() != null && !feed.getFeedImages().isEmpty()) {
            setupImageSlider(feed.getFeedImages());
        }

        // 해시태그 설정
        if (feed.getHashtags() != null && !feed.getHashtags().isEmpty()) {
            addHashtags(feed.getHashtags());
        }

        // 팀원 데이터 설정
        List<Member> members = new ArrayList<>();
        if (feed.getProject() != null && feed.getProject().getProjectRoles() != null) {
            for (FeedDTO.FeedProjectDTO.ProjectRoleDTO role : feed.getProject().getProjectRoles()) {
                members.add(new Member(
                        R.drawable.member_image, // 기본 이미지
                        role.getUserId().toString(), // 팀원 이름
                        role.getRole() // 팀원 역할
                ));
            }
        } else {
            // 프로젝트가 없을 경우 작성자 정보만 추가
            FeedDTO.FeedUserDTO user = feed.getUser();
            members.add(new Member(
                    R.drawable.member_image, // 기본 이미지
                    user.getName(),
                    "작성자"
            ));
        }

        // RecyclerView에 어댑터 설정
        memberAdapter = new MemberAdapter(members, feed);
        recyclerView.setAdapter(memberAdapter);

        if (feed != null) {
            MemberAdapter adapter = new MemberAdapter(members, feed);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Log.e("Adapter Error", "FeedDTO is null");
        }

        // 전시 정보 설정
        if (feed.getExhibition() != null) {
            FeedDTO.FeedExhibitionDTO exhibition = feed.getExhibition();
            TextView exhibitionLocation = findViewById(R.id.item_detail_exhibition_location);
            TextView exhibitionDate = findViewById(R.id.item_detail_exhibition_date);
            TextView exhibitionTime = findViewById(R.id.item_detail_exhibition_time);

            exhibitionLocation.setText(exhibition.getLocation());
            exhibitionDate.setText(exhibition.getSchedule());
            exhibitionTime.setText(exhibition.getTime());
        }
    }

    private void addTeamMemberToUI(LinearLayout teamMembersLayout, String name, String role) {
        View teamMemberView = getLayoutInflater().inflate(R.layout.item_member, teamMembersLayout, false);

        ImageView memberImage = teamMemberView.findViewById(R.id.member_image);
        TextView memberName = teamMemberView.findViewById(R.id.member_name);
        TextView memberRole = teamMemberView.findViewById(R.id.member_role);

        memberImage.setImageResource(R.drawable.member_image);
        memberName.setText(name);
        memberRole.setText(role);

        teamMembersLayout.addView(teamMemberView);
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

    private void addHashtags(Set<FeedDTO.FeedHashtagDTO> hashtags) {
        for (FeedDTO.FeedHashtagDTO hashtag : hashtags) {
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

    private void toggleScrap() {
        scrapButton.setEnabled(false); // 버튼 비활성화
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ScrapResponse> call = isScrapped
                ? apiService.unscrapFeed(feedId, null, null) // 스크랩 취소
                : apiService.scrapFeed(feedId, null, null);  // 스크랩

        call.enqueue(new Callback<ScrapResponse>() {
            @Override
            public void onResponse(Call<ScrapResponse> call, Response<ScrapResponse> response) {
                scrapButton.setEnabled(true); // 버튼 다시 활성화
                if (response.isSuccessful() && response.body() != null) {
                    ScrapResponse scrapResponse = response.body();
                    isScrapped = !isScrapped; // 상태 토글
                    scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);
                    scrap.setText(String.valueOf(scrapResponse.getScrapCount()));
                    Toast.makeText(ItemDetailActivity.this, scrapResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    handleError(response.code());
                }
            }

            @Override
            public void onFailure(Call<ScrapResponse> call, Throwable t) {
                scrapButton.setEnabled(true); // 요청 실패 시 버튼 활성화
                Toast.makeText(ItemDetailActivity.this, "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleError(int errorCode) {
        switch (errorCode) {
            case 400:
                Toast.makeText(this, "이미 스크랩한 피드입니다.", Toast.LENGTH_SHORT).show();
                break;
            case 404:
                Toast.makeText(this, "게시물을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "스크랩 상태를 변경하지 못했습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onHashtagClicked(View view) {
        TextView textView = (TextView) view;
        String hashtag = textView.getText().toString().substring(1); // "#" 제거

        searchByHashtag(hashtag); // 해시태그 검색 메서드 호출
    }

    // 해시태그 검색
    private void searchByHashtag(String hashtag) {
        if (!isTokenValid()) return; // 유효한 토큰 여부 확인

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<FeedDTO>> call = apiService.searchFeedsByHashtag(hashtag);

        call.enqueue(new Callback<List<FeedDTO>>() {
            @Override
            public void onResponse(Call<List<FeedDTO>> call, Response<List<FeedDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // 검색 결과가 성공적으로 반환된 경우
                    Intent intent = new Intent(ItemDetailActivity.this, SearchResultActivity.class);
                    intent.putExtra("searchResults", (Serializable) response.body());
                    intent.putExtra("hashtag", hashtag);
                    startActivity(intent);
                } else {
                    // 검색 결과가 없는 경우
                    Toast.makeText(ItemDetailActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FeedDTO>> call, Throwable t) {
                // 네트워크 오류 발생 시 처리
                Toast.makeText(ItemDetailActivity.this, "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isTokenValid() {
        String accessToken = TokenManager.getToken(); // TokenManager를 사용해 토큰 가져오기
        if (accessToken == null || accessToken.isEmpty()) {
            Toast.makeText(this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public void onMemberClicked(View view) {
        String userType = TokenManager.getRole();
        Intent intent;
        if (userType.equals("RECRUITER")) {
            intent = new Intent(this, ItemDetailMemberRecruiterActivity.class);
        } else {
            intent = new Intent(this, ItemDetailMemberGeneralActivity.class);
        }
        startActivity(intent);

    }
}