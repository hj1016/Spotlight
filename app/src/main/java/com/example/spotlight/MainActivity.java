package com.example.spotlight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.spotlight.network.API.*;
import com.example.spotlight.network.Request.FeedRequest;
import com.example.spotlight.network.Response.FeedResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private boolean isScrapped = false;
    private SharedPreferences sharedPreferences;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();


        sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        checkLoginStatus();

        sharedPreferences = getSharedPreferences("UserType", MODE_PRIVATE);

        if (getIntent().getBooleanExtra("navigateToMyPage", false)) {
            navigateToMyPage();
        } else if (getIntent().getBooleanExtra("shouldNavigateToHome", false)) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
            bottomNavigationView.setSelectedItemId(R.id.menu_home);
        } else {
            // 초기 화면 설정
            String fragmentToLoad = getIntent().getStringExtra("Fragment");
            if (fragmentToLoad != null) {
                loadFragment(fragmentToLoad);
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
                bottomNavigationView.setSelectedItemId(R.id.menu_home);
            }
        }
    }

    private void loadFragment(String fragmentName) {
        Fragment selectedFragment = null;
        int selectedMenuItemId = R.id.menu_home; // 기본값으로 홈 메뉴 설정
        switch (fragmentName) {
            case "MyPageRecruiterFragment":
                selectedFragment = new MyPageRecruiterFragment();
                selectedMenuItemId = R.id.menu_mypage;
                break;
            case "MyPageFragment":
                selectedFragment = new MyPageFragment();
                selectedMenuItemId = R.id.menu_mypage;
                break;
            default:
                selectedFragment = new HomeFragment();
                selectedMenuItemId = R.id.menu_home;
                break;
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            bottomNavigationView.setSelectedItemId(selectedMenuItemId);
        }
    }

    private void setupBottomNavigationView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.menu_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.menu_search) {
                selectedFragment = new SearchFragment();
            } else if (itemId == R.id.menu_stage) {
                selectedFragment = new StageFragment();
            } else if (itemId == R.id.menu_mypage) {
                navigateToMyPage();
                return true;
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });
    }


    private void checkLoginStatus() {
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }

    private void navigateToMyPage() {
        String userType = sharedPreferences.getString("Type", "Default");
        Fragment fragment;
        if (userType.equals("Recruiter")) {
            fragment = new MyPageRecruiterFragment();
        } else {
            fragment = new MyPageFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    public void onBackClicked(View view) {
        String userType = sharedPreferences.getString("userType", "general");
        Intent intent = new Intent(this, MainActivity.class);
        if ("Recruiter".equals(userType)) {
            intent.putExtra("Fragment", "MyPageRecruiterFragment");
        } else {
            intent.putExtra("Fragment", "MyPageFragment");
        }
        startActivity(intent);
        finish();
    }

    public void onContinueGraduatesSignupClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onContinueRecruiterSignup1Clicked(View view) {
        Intent intent = new Intent(this, RecruiterStep2Activity.class);
        startActivity(intent);
    }

    public void onContinueRecruiterSignup2Clicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("shouldNavigateToHome", true);  // 인텐트에 플래그 추가
        startActivity(intent);
    }

    public void onContinueSchoolSearchClicked(View view) {
        Intent intent = new Intent(this, SearchResultActivity.class);
        startActivity(intent);
    }

    public void onStageClicked(View view) {
        Intent intent = new Intent(this, StageDetailActivity.class);
        startActivity(intent);
    }

    public void onAlarmClicked(View view) {
        Intent intent = new Intent(this, AlarmActivity.class);
        startActivity(intent);
    }

    public void onProfileClicked(View view) {
        Intent intent = new Intent(this, ProfileGraduatesActivity.class);
        startActivity(intent);
    }

    public void onManagePostingClicked(View view) {
        Intent intent = new Intent(this, ManagePostingActivity.class);
        startActivity(intent);
    }

    public void onPortfolioClicked(View view) {
        Intent intent = new Intent(this, MyPagePortfolioActivity.class);
        startActivity(intent);
    }

    // 게시물 작성
    public void onCompleteClicked(View view) {
        // UI에서 데이터 수집
        String title = ((EditText) findViewById(R.id.new_posting_project_text)).getText().toString();
        String image = "URL"; // 이미지는 나중에 처리
        String content = ((EditText) findViewById(R.id.new_posting_description_text)).getText().toString();
        String bigCategory = ((Spinner) findViewById(R.id.big_category_spinner)).getSelectedItem().toString();
        String smallCategory = ((Spinner) findViewById(R.id.small_category_spinner)).getSelectedItem().toString();
        String hashtags = ((EditText) findViewById(R.id.new_posting_hashtag_text)).getText().toString();

        // 요청 객체 생성
        FeedRequest feedRequest = new FeedRequest();
        feedRequest.setTitle(title);
        feedRequest.setImage(image);
        feedRequest.setContent(content);
        feedRequest.setScrap(0);  // 기본값

        // 카테고리 설정
        FeedRequest.Category category = new FeedRequest.Category();
        category.setMain(bigCategory);
        category.setSub(smallCategory);
        feedRequest.setCategory(category);

        // 해시태그 설정
        List<String> hashtagList = Arrays.asList(hashtags.split(","));
        feedRequest.setHashtag(hashtagList);

        // 게시물 작성 요청
        Call<FeedResponse> call = apiService.createFeed(feedRequest);
        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(MainActivity.this, "게시물이 성공적으로 작성되었습니다!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, NewPostingActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "게시물 작성에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "오류가 발생했습니다: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 게시물 수정
    public void onCompletePostingEditClicked(View view) {
        // 게시물 ID 가져오기
        Intent intent = getIntent();
        int feedId = intent.getIntExtra("FEED_ID", -1); // -1은 기본값으로 설정하여 만약 ID가 전달되지 않으면 오류를 방지합니다.

        // UI에서 데이터 수집
        String title = ((EditText) findViewById(R.id.posting_edit_project_text)).getText().toString();
        String image = "URL"; // 이미지는 나중에 처리
        String content = ((EditText) findViewById(R.id.posting_edit_description_text)).getText().toString();
        String bigCategory = ((Spinner) findViewById(R.id.posting_edit_big_category_spinner)).getSelectedItem().toString();
        String smallCategory = ((Spinner) findViewById(R.id.posting_edit_small_category_spinner)).getSelectedItem().toString();
        String hashtags = ((EditText) findViewById(R.id.posting_edit_hashtag_text)).getText().toString();

        // 요청 객체 생성
        FeedRequest feedRequest = new FeedRequest();
        feedRequest.setTitle(title);
        feedRequest.setImage(image);
        feedRequest.setContent(content);
        feedRequest.setScrap(0);  // 기본값

        // 카테고리 설정
        FeedRequest.Category category = new FeedRequest.Category();
        category.setMain(bigCategory);
        category.setSub(smallCategory);
        feedRequest.setCategory(category);

        // 해시태그 설정
        List<String> hashtagList = Arrays.asList(hashtags.split(","));
        feedRequest.setHashtag(hashtagList);

        // 게시물 수정 요청
        Call<FeedResponse> call = apiService.updateFeed(feedId, feedRequest);
        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(MainActivity.this, "게시물이 성공적으로 수정되었습니다!", Toast.LENGTH_SHORT).show();
                    // 게시물 수정 후 현재 화면 종료
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "게시물 수정에 실패했습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "오류가 발생했습니다: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onInviteClicked(View view) {
        Intent intent = new Intent(this, NewPostingActivity.class);
        startActivity(intent);
    }

    public void onMemberPlusClicked(View view) {
        Intent intent = new Intent(this, NewPostingMemberActivity.class);
        startActivity(intent);
    }

    public void onExhibitionPlusClicked(View view) {
        Intent intent = new Intent(this, NewPostingExhibitionActivity.class);
        startActivity(intent);
    }

    public void toggleScrap(View view) {
        ImageView scrapButton = (ImageView) view;
        isScrapped = !isScrapped;
        scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);
    }

    public void onSearchSchoolClicked(View view) {
        Intent intent = new Intent(this, SearchSchoolActivity.class);
        startActivity(intent);
    }

    public void onScrapProjectClicked(View view) {
        Intent intent = new Intent(this, ScrapProjectActivity.class);
        startActivity(intent);
    }

    public void onScrapStageClicked(View view) {
        Intent intent = new Intent(this, ScrapStageActivity.class);
        startActivity(intent);
    }

    public void onScrapGraduatesClicked(View view) {
        Intent intent = new Intent(this, ScrapGraduatesActivity.class);
        startActivity(intent);
    }

    public void onProposeClicked(View view) {
        Intent intent = new Intent(this, GraduatesProposeActivity.class);
        startActivity(intent);
    }

    public void onMemberClicked(View view) {
        String userType = sharedPreferences.getString("Type", "general");
        Intent intent;
        if (userType.equals("recruiter")) {
            intent = new Intent(this, ItemDetailMemberRecruiterActivity.class);
        } else {
            intent = new Intent(this, ItemDetailMemberGeneralActivity.class);
        }
        startActivity(intent);
    }

    public void onReceivedProposeClicked(View view) {
        Intent intent = new Intent(this, ItemDetailMemberRecruiterActivity.class);
        startActivity(intent);
    }

}