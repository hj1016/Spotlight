package com.example.spotlight.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;
import com.example.spotlight.common.CustomExpandableListAdapter;
import com.example.spotlight.common.VerticalSpaceItemDecoration;
import com.example.spotlight.posting.Post;
import com.example.spotlight.posting.PostAdapter;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ExpandableListView expandableListView;
    private CustomExpandableListAdapter adapter;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> posts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_feed, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        postAdapter = new PostAdapter(getContext(), posts);
        recyclerView.setAdapter(postAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(0));

        posts = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), posts);
        recyclerView.setAdapter(postAdapter);

        expandableListView = view.findViewById(R.id.expandableListView);
        setupExpandableListView();

        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        setupDrawerNavigation(view);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // String childName = getChildName(groupPosition, childPosition); // 자식의 이름 가져오기 예시 메서드

                //Intent intent = new Intent(getContext(), ElectronicActivity.class);
                // intent.putExtra("childName", childName);
                //startActivity(intent);

                return true;
            }
        });
    }

    private void setupDrawerNavigation(View view) {
        ImageButton categoryButton = view.findViewById(R.id.category_button);
        categoryButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.END));
    }

    private void setupExpandableListView() {
        String[] categories = getResources().getStringArray(R.array.big_categories);
        String[] humanitiesSubcategories = getResources().getStringArray(R.array.small_categories_humanities);
        String[] socialSubcategories = getResources().getStringArray(R.array.small_categories_social);
        String[] educationSubcategories = getResources().getStringArray(R.array.small_categories_education);
        String[] natureSubcategories = getResources().getStringArray(R.array.small_categories_nature);
        String[] engineeringSubcategories = getResources().getStringArray(R.array.small_categories_engineering);
        String[] medicalSubcategories = getResources().getStringArray(R.array.small_categories_medical);
        String[] entertainmentSubcategories = getResources().getStringArray(R.array.small_categories_entertainment);

        List<String> listDataHeader = new ArrayList<>();
        HashMap<String, List<String>> listDataChild = new HashMap<>();

        listDataHeader.add("전체");
        listDataChild.put("전체", new ArrayList<>()); // 전체 카테고리에 대한 하위 카테고리 설정

        for (String category : categories) {
            listDataHeader.add(category);
        }

        List<String[]> subcategoriesArray = new ArrayList<>();
        subcategoriesArray.add(new String[0]);
        subcategoriesArray.add(humanitiesSubcategories);
        subcategoriesArray.add(socialSubcategories);
        subcategoriesArray.add(educationSubcategories);
        subcategoriesArray.add(natureSubcategories);
        subcategoriesArray.add(engineeringSubcategories);
        subcategoriesArray.add(medicalSubcategories);
        subcategoriesArray.add(entertainmentSubcategories);

        for (int i = 0; i < listDataHeader.size(); i++) {
            List<String> subcategoryList = new ArrayList<>();
            if (i > 0) {
                for (String subcategory : subcategoriesArray.get(i)) {
                    subcategoryList.add(subcategory);
                }
            }
            listDataChild.put(listDataHeader.get(i), subcategoryList);
        }

        adapter = new CustomExpandableListAdapter(getContext(), listDataHeader, listDataChild);
        expandableListView.setAdapter(adapter);

        adapter.setSelectedGroupPosition(0);
        expandableListView.expandGroup(0);

        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            if (adapter.getSelectedGroupPosition() == groupPosition) {
                expandableListView.collapseGroup(groupPosition);
                adapter.setSelectedGroupPosition(-1);
            } else {
                expandableListView.collapseGroup(adapter.getSelectedGroupPosition());
                expandableListView.expandGroup(groupPosition);
                adapter.setSelectedGroupPosition(groupPosition);
            }
            adapter.notifyDataSetChanged();
            return true;
        });

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            // 전기/전자 카테고리를 클릭했을 때의 처리
            if (groupPosition == 4 && childPosition == 9) { // 전기/전자 카테고리의 위치 확인
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).loadFragment("ElectronicFragment"); // MainActivity로 이동 요청
                }
            }
            return true;
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.END);
        return true;
    }
}