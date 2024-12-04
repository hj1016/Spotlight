package com.example.spotlight.scrap;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;
import com.example.spotlight.graduates.Graduate;
import com.example.spotlight.graduates.GraduateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScrapGraduatesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GraduateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrap_graduates);

        recyclerView = findViewById(R.id.recyclerView_scrap_graduates);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Graduate> graduates = new ArrayList<>();
        Graduate graduate = new Graduate();
        graduate.setName("난학생");
        graduate.setFeedTitle("DanDan");
        graduate.setProfileImage("https://storage.googleapis.com/spotlight-c7eb0.appspot.com/user_documents%2Fe854b848-6b07-4dfe-93df-6ed0afb20b93_images.jpeg");
        graduates.add(graduate);

        adapter = new GraduateAdapter(this, graduates, new GraduateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Graduate clickedGraduate = graduates.get(position);
                Toast.makeText(ScrapGraduatesActivity.this, "Clicked: " + clickedGraduate.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void onBackClicked(View view) {
        finish();
    }
}