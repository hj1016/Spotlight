package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;

// Adapter와 ViewHolder 클래스를 포함하는 추가 import
import com.example.spotlight.GraduateAdapter;  // 이는 GraduateAdapter가 다른 패키지에 위치한 경우 필요합니다.
import com.example.spotlight.Graduate;  // 이는 Graduate 클래스가 다른 패키지에 위치한 경우 필요합니다.


public class ScrapGraduatesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GraduateAdapter adapter;
    private List<Graduate> graduates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrap_graduates);

        recyclerView = findViewById(R.id.recyclerView_scrap_graduates);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        graduates = new ArrayList<>();
        graduates.add(new Graduate("@drawable/sample_image", "홍길동", "프로젝트 A"));
        graduates.add(new Graduate("@drawable/sample_image", "이순신", "프로젝트 B"));

        adapter = new GraduateAdapter(this, graduates);
        recyclerView.setAdapter(adapter);
    }
}

