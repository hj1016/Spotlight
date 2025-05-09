package com.example.spotlight.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.spotlight.chatbot.ChatbotActivity;
import com.example.spotlight.posting.ItemDetailMemberGeneralActivity;
import com.example.spotlight.posting.ItemDetailMemberRecruiterActivity;
import com.example.spotlight.posting.ManagePostingActivity;
import com.example.spotlight.mypage.MyPagePortfolioActivity;
import com.example.spotlight.mypage.MyPageRecruiterFragment;
import com.example.spotlight.posting.NewPostingExhibitionActivity;
import com.example.spotlight.posting.NewPostingMemberActivity;
import com.example.spotlight.R;
import com.example.spotlight.authentication.RecruiterStep2Activity;
import com.example.spotlight.scrap.ScrapProjectActivity;
import com.example.spotlight.scrap.ScrapStageActivity;
import com.example.spotlight.search.SearchFragment;
import com.example.spotlight.search.SearchResultActivity;
import com.example.spotlight.search.SearchSchoolActivity;
import com.example.spotlight.stage.StageDetailActivity;
import com.example.spotlight.stage.StageFragment;
import com.example.spotlight.alarm.AlarmActivity;
import com.example.spotlight.authentication.LoginActivity;
import com.example.spotlight.graduates.GraduatesProposeActivity;
import com.example.spotlight.profile.ProfileGraduatesActivity;
import com.example.spotlight.network.Util.TokenManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ImageView floatingButton;
    private BottomNavigationView bottomNavigationView;
    private EditText searchBarText;
    private boolean isScrapped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();

        // 플로팅 버튼 초기화
        floatingButton = findViewById(R.id.floating_button);

        // 플로팅 버튼 클릭 이벤트
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ChatbotActivity로 이동
                Intent intent = new Intent(MainActivity.this, ChatbotActivity.class);
                startActivity(intent);
            }
        });

        checkLoginStatus();

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

    public void loadFragment(String fragmentName) {
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
                updateBottomNavigationIcons(itemId);
            }
            return true;
        });
    }

    private void updateBottomNavigationIcons(int selectedItemId) {
        MenuItem homeItem = bottomNavigationView.getMenu().findItem(R.id.menu_home);
        MenuItem searchItem = bottomNavigationView.getMenu().findItem(R.id.menu_search);
        MenuItem stageItem = bottomNavigationView.getMenu().findItem(R.id.menu_stage);
        MenuItem mypageItem = bottomNavigationView.getMenu().findItem(R.id.menu_mypage);

        homeItem.setIcon(R.drawable.home_no_click);
        searchItem.setIcon(R.drawable.search_no_click);
        stageItem.setIcon(R.drawable.stage_no_click);
        mypageItem.setIcon(R.drawable.mypage_no_click);

        if (selectedItemId == R.id.menu_home) {
            homeItem.setIcon(R.drawable.home_click);
        } else if (selectedItemId == R.id.menu_search) {
            searchItem.setIcon(R.drawable.search_click);
        } else if (selectedItemId == R.id.menu_stage) {
            stageItem.setIcon(R.drawable.stage_click);
        } else if (selectedItemId == R.id.menu_mypage) {
            mypageItem.setIcon(R.drawable.mypage_click);
        }
    }

    private void checkLoginStatus() {
        boolean isLoggedIn = !TokenManager.getToken().isEmpty();
        Log.d("MainActivity","checkLoginStatus");
        if (!isLoggedIn) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }

    private void navigateToMyPage() {
        String userType = TokenManager.getRole();
        Log.d("userType", userType);
        Fragment fragment = null;
        if (userType.equals("RECRUITER")) {
            fragment = new MyPageRecruiterFragment();
        } else {
            fragment = new MyPageFragment();
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            updateBottomNavigationIcons(R.id.menu_mypage);
        }
    }

    public void onBackClicked(View view) {
        String userType = TokenManager.getRole();
        Intent intent = new Intent(this, MainActivity.class);
        if ("RECRUITER".equals(userType)) {
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

    /*
    public void onScrapGraduatesClicked(View view) {
        Intent intent = new Intent(this, ScrapGraduatesActivity.class);
        startActivity(intent);
    }

     */

    public void onProposeClicked(View view) {
        Intent intent = new Intent(this, GraduatesProposeActivity.class);
        startActivity(intent);
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

    public void onReceivedProposeClicked(View view) {
        Intent intent = new Intent(this, ItemDetailMemberRecruiterActivity.class);
        startActivity(intent);
    }

    public void onLogoutClicked(View view) {
        TokenManager.clearToken();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onSearchButtonClicked(View view){
        Intent intent = new Intent(this, SearchResultActivity.class);
        startActivity(intent);
    }
}