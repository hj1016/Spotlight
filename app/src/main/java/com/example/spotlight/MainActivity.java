package com.example.spotlight;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.view.View;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private boolean isScrapped = false;
    private SharedPreferences sharedPreferences;


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
        }

        if (getIntent().getBooleanExtra("shouldNavigateToHome", false)) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
            bottomNavigationView.setSelectedItemId(R.id.menu_home);
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


    /*private void checkLoginStatus() {
        // SharedPreferences를 사용하여 로그인 여부 확인
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            // 사용자가 로그인하지 않았다면 로그인 화면으로 이동
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
            finish(); // MainActivity 종료
        } else {
            // 로그인 상태라면 바로 HomeFragment로
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }*/

    //백엔드 연결 전 확인 용 코드, 백엔드 연결 시 위의 것으로 교체
    private void checkLoginStatus() {
    // SharedPreferences를 사용하여 로그인 여부 확인
    boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
    } else {
        // 로그인 상태라면 바로 HomeFragment로
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
        } else { // "Default" 포함 나머지 모든 경우
            fragment = new MyPageFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }



    // Method to reset icon state of other items
    private void resetIconState(int itemId) {
        // Reset icon state of all items
        if (itemId != R.id.menu_home) {
            bottomNavigationView.getMenu().findItem(R.id.menu_home).setIcon(R.drawable.home_no_click);
        }
        if (itemId != R.id.menu_search) {
            bottomNavigationView.getMenu().findItem(R.id.menu_search).setIcon(R.drawable.search_no_click);
        }
        if (itemId != R.id.menu_stage) {
            bottomNavigationView.getMenu().findItem(R.id.menu_stage).setIcon(R.drawable.stage_no_click);
        }
        if (itemId != R.id.menu_mypage) {
            bottomNavigationView.getMenu().findItem(R.id.menu_mypage).setIcon(R.drawable.mypage_no_click);
        }
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

    public void onCompleteClicked(View view) {
        Intent intent = new Intent(this, NewPostingActivity.class);
        startActivity(intent);
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
        isScrapped = !isScrapped; // Toggle the state
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



}
