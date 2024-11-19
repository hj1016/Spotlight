package com.example.spotlight.posting;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

import com.example.spotlight.R;

public class ManagePostingGeneralActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_posting_not_graduates);
    }
    public void onBackClicked(View view) {
        finish();
    }
}
