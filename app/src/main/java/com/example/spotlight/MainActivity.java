package com.example.spotlight;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.view.View;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private List<Post> posts;
    private BottomNavigationView bottomNavigationView;
    private boolean isScrapped = false;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("UserType", MODE_PRIVATE);

        if (getIntent().getBooleanExtra("navigateToMyPage", false)) {
            navigateToMyPage();
        }


        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setup the adapter with sample posts
        posts = new ArrayList<>();
        posts.add(new Post(
                "@drawable/image_basic",
                "You little human",
                "사진/영상",
                "@drawable/image_ex1",
                "On a blazingly sunny morning in March, the 22-year-old Italian tennis star Jannik Sinner could be found on the sprawling grounds of a ranch-style home he’d rented in the Coachella Valley... read more",
                132,
                Arrays.asList("#A.E.S"), // 문자열을 리스트로 변환
                "@drawable/scrap_no"
        ));

        adapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(adapter);

        // Setup BottomNavigationView
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        });

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.menu_home);
        }
    }

    private void navigateToMyPage() {
        String userType = sharedPreferences.getString("Type", "Default"); // 기본 유형을 "Default"로 설정
        Intent intent;
        if (userType.equals("Recruiter")) {
            intent = new Intent(this, MyPageRecruiterActivity.class);
        } else { // "Default" 포함 나머지 모든 경우
            intent = new Intent(this, MyPageActivity.class);
        }
        startActivity(intent);
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
        Intent intent = new Intent(this, MyPageActivity.class);
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
