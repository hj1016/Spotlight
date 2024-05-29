package com.example.spotlight;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private List<Post> posts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 뷰 인플레이션
        return inflater.inflate(R.layout.home_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 리사이클러뷰 초기화
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 데이터 목록 생성 및 어댑터 설정
        posts = new ArrayList<>();
        initializeData();
        adapter = new PostAdapter(getContext(), posts);
        recyclerView.setAdapter(adapter);
    }

    private void initializeData() {
        // 데이터 초기화 로직, 샘플 데이터 추가
        posts.add(new Post("@drawable/sample_image", "Project Title 1", "Category 1", "@drawable/image1",
                "Description of project 1.", 42, Arrays.asList("#tag1", "#tag2"), "@drawable/icon", false));
        posts.add(new Post("@drawable/sample_image", "Project Title 2", "Category 2", "@drawable/image2",
                "Description of project 2.", 52, Arrays.asList("#tag3", "#tag4"), "@drawable/icon", false));
        // 추가 데이터 로드 가능
    }
}
