package com.example.spotlight.scrap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
/*
// Adapter와 ViewHolder 클래스를 포함하는 추가 import
import com.example.spotlight.GraduateAdapter;  // 이는 GraduateAdapter가 다른 패키지에 위치한 경우 필요합니다.
import com.example.spotlight.R;
import com.example.spotlight.graduates.Graduate;
import com.example.spotlight.network.API.ApiService;


public class ScrapGraduatesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GraduateAdapter adapter;
    private List<Graduate> graduates;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrap_graduates);

//        recyclerView = findViewById(R.id.recyclerView_scrap_graduates);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        apiService = ApiClient.getClientWithToken().create(ApiService.class);
//
//        Call<List<ScrapDTO>> call = apiService.getScrapStudents();
//        call.enqueue(new Callback<List<ScrapDTO>>() {
//            @Override
//            public void onResponse(Call<List<ScrapDTO>> call, Response<List<ScrapDTO>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    List<ScrapDTO> scrapList = response.body();
//                    for (ScrapDTO scrap : scrapList) {
//                        graduates.add(new Graduate(scrap.getProfileImage(), scrap.getUsername(), scrap.getProjectRole()));
//                    }
//                } else {
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ScrapDTO>> call, Throwable t) {
//            }
//        });
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onMemberClicked(View view) {
        Intent intent = new Intent(ScrapGraduatesActivity.this, FakeScrapGraduatesActivity.class);
        startActivity(intent);
    }
}

 */