package com.example.spotlight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private boolean isScrapped = false;
    private SharedPreferences sharedPreferences;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

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

    public void onCompleteClicked(View view) {
        Intent intent = new Intent(this, NewPostingActivity.class);
        startActivity(intent);
    }
    public void onCompletePostingEditClicked(View view) {
        finish();
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
