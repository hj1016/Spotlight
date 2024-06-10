package com.example.spotlight;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ElectronicActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ExpandableListView expandableListView;
    private CustomExpandableListAdapter adapter;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic);

        drawerLayout = findViewById(R.id.drawer_layout);
        expandableListView = findViewById(R.id.expandableListView);
        recyclerView = findViewById(R.id.recyclerView);

        // Setting up the expandable list view
        setupExpandableListView();

        // Setting up the navigation drawer
        setupDrawerNavigation();

        // Setting up the RecyclerView with electrical posts
        setupRecyclerView();
    }

    private void setupExpandableListView() {
        String[] categories = getResources().getStringArray(R.array.big_categories);
        String[] engineeringSubcategories = getResources().getStringArray(R.array.small_categories_engineering);

        List<String> listDataHeader = new ArrayList<>();
        HashMap<String, List<String>> listDataChild = new HashMap<>();

        listDataHeader.add("전체");
        for (String category : categories) {
            listDataHeader.add(category);
        }

        List<String> subcategoryList = new ArrayList<>();
        for (String subcategory : engineeringSubcategories) {
            subcategoryList.add(subcategory);
        }
        listDataChild.put(listDataHeader.get(5), subcategoryList);

        adapter = new CustomExpandableListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(adapter);

        // Expand the first group by default
        expandableListView.expandGroup(5);

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            // 전기/전자 카테고리를 클릭했을 때의 처리
            if (groupPosition == 5 && childPosition == 9) { // 전기/전자 카테고리의 위치 확인
                // Handle the click event
                return true;
            }
            return false;
        });
    }

    private void setupDrawerNavigation() {
        ImageButton categoryButton = findViewById(R.id.category_button);
        categoryButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.END));

        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(item -> {
                drawerLayout.closeDrawer(GravityCompat.END);
                return true;
            });
        }
    }

    private void setupRecyclerView() {
        posts = getElectricalPosts();
        postAdapter = new PostAdapter(this, posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }

    private List<Post> getElectricalPosts() {
        List<Post> electricalPosts = new ArrayList<>();
        electricalPosts.add(new Post(R.drawable.team_image, "Spectroscopy", "전기/전자", R.drawable.electronics,
                "We examine the basic principles of absorption spectroscopy and characteristics of absorption ...", 37, Arrays.asList("#SNU", "#Electronics", "Electrotechnics"), R.drawable.scrap_no, false));
        electricalPosts.add(new Post(R.drawable.team_image, "Spectroscopy Report", "전기/전자", R.drawable.chemical,
                "The absorption and radiation of light by the material is divided into spectra using a spectrometer ...", 29, Arrays.asList("#Electronics", "#SNU", "Experiment"), R.drawable.scrap_no, false));
        return electricalPosts;
    }
}