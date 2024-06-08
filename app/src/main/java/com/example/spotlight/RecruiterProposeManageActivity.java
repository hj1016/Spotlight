package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;

public class RecruiterProposeManageActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RecruiterProposeAdapter adapter;
    private List<RecruiterProposal> proposerecruiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruiter_propose_manage);

        recyclerView = findViewById(R.id.recyclerView_recruiter_propose_manage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        proposerecruiter = new ArrayList<>();
        proposerecruiter.add(new RecruiterProposal("@drawable/sample_image", "홍길동", "project1", "company1","today","role1"));
        proposerecruiter.add(new RecruiterProposal("@drawable/sample_image", "이순신", "project2", "company2","today","role2"));

        adapter = new RecruiterProposeAdapter(this, proposerecruiter); // 올바른 Adapter를 사용
        recyclerView.setAdapter(adapter);
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onEditProposeClicked(View view) {
        Intent intent = new Intent(this, RecruiterProposeEditActivity.class);
        startActivity(intent);
    }
}