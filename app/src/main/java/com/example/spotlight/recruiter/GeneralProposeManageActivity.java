package com.example.spotlight.recruiter;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

import com.example.spotlight.R;

public class GeneralProposeManageActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_propose_manage);
    }

    public void onBackClicked(View view) {
        finish();
    }
}
