package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class StageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stage_main);

        ImageView stageContent1 = findViewById(R.id.stage_content1);

        stageContent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // StageDetailActivity를 시작하는 Intent 생성
                Intent intent = new Intent(StageActivity.this, StageDetailActivity.class);
                // Intent를 사용하여 StageDetailActivity 시작
                startActivity(intent);
            }
        });
    }
}
