package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;

public class GraduatesProposeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GraduatesProposeAdapter adapter;
    private List<GraduatesProposal> proposegraduates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graduates_propose_manage);

        recyclerView = findViewById(R.id.recyclerView_graduates_propose_manage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        proposegraduates = new ArrayList<>();
        proposegraduates.add(new GraduatesProposal("company1", "홍길동", "today", "@drawable/sample_image"));
        proposegraduates.add(new GraduatesProposal("company2", "이순신", "today", "@drawable/sample_image"));

        adapter = new GraduatesProposeAdapter(this, proposegraduates);
        recyclerView.setAdapter(adapter);
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onReceivedProposeClicked(View view) {
        Intent intent = new Intent(this, ItemDetailMemberRecruiterActivity.class);
        startActivity(intent);
    }
}
