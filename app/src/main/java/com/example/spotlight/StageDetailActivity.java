package com.example.spotlight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;

public class StageDetailActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private ImageButton topButton;
    private boolean isScrapped = false;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stage_detail); // stage_detail.xml 파일과 연결
        sharedPreferences = getSharedPreferences("UserType", MODE_PRIVATE);

        scrollView = findViewById(R.id.scrollView2);
        topButton = findViewById(R.id.stage_top_Button);

        // 상단 이동 버튼 클릭 이벤트 설정
        topButton.setOnClickListener(v -> {
            // ScrollView를 맨 위로 스크롤
            scrollView.smoothScrollTo(0, 0);
        });
    }
    public void onBackClicked(View view) {
        Intent intent = new Intent(this, StageActivity.class);
        startActivity(intent);
    }

    public void toggleScrap(View view) {
        ImageView scrapButton = (ImageView) view;
        isScrapped = !isScrapped; // Toggle the state
        scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);
    }

    public void onMemberClicked(View view) {
        String userType = sharedPreferences.getString("Type", "general"); // 기본값을 "general"로 설정
        Intent intent;
        if (userType.equals("recruiter")) {
            intent = new Intent(this, ItemDetailMemberRecruiterActivity.class);
        } else {
            intent = new Intent(this, ItemDetailMemberGeneralActivity.class);
        }
        startActivity(intent);
    }

}
