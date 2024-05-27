package com.example.spotlight;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import androidx.annotation.NonNull;
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

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private List<Post> posts;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setup the adapter with sample posts
        posts = new ArrayList<>();
        posts.add(new Post("@drawable/image_basic", "You little human", "사진/영상", "@drawable/image_ex1",
                " On a blazingly sunny morning in March, the 22-year-old Italian tennis star Jannik Sinner could be found on the sprawling grounds of a ranch-style home he’d rented in the Coachella Valley... read more", 132, "#A.E.S","@drawable/scrap_no"));

        adapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(adapter);

        // Setup BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment;

            if (item.getItemId() == R.id.menu_home) {
                selectedFragment = new HomeFragment();
                item.setIcon(R.drawable.home_click); // Change icon when clicked
            } else if (item.getItemId() == R.id.menu_search) {
                selectedFragment = new SearchFragment();
                item.setIcon(R.drawable.search_click); // Change icon when clicked
            } else if (item.getItemId() == R.id.menu_stage) {
                selectedFragment = new StageFragment();
                item.setIcon(R.drawable.stage_click); // Change icon when clicked
            } else if (item.getItemId() == R.id.menu_mypage) {
                selectedFragment = new MyPageFragment();
                item.setIcon(R.drawable.mypage_click); // Change icon when clicked
            } else {
                return false;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            // Reset icon state of other items
            resetIconState(item.getItemId());

            return true;
        });

        // Default fragment to display (HomeFragment)
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        // Set the home menu item as selected
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
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




}
