package com.example.spotlight.stage;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.R;

public class StageRecruiterProposeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stage_recruiter_propose);

    }
    public void onBackClicked(View view) {
        finish();
    }
}
