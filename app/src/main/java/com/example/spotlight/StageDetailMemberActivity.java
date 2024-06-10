package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StageDetailMemberActivity extends AppCompatActivity {

    private boolean isScrapped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stage_detail_member);

        TextView textView = findViewById(R.id.item_detail_member_recruiter_propose);
        ImageView backButton = findViewById(R.id.item_detail_member_recruiter_back);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StageDetailMemberActivity.this, StageRecruiterProposeActivity.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void toggleScrap(View view) {
        ImageView scrapButton = (ImageView) view;
        isScrapped = !isScrapped;
        scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);
    }
}
