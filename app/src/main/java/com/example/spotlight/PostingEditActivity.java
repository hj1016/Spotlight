package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class PostingEditActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_edit);
    }
    public void onBackClicked(View view) {
        finish();
    }

    public void onCompleteClicked(View view) {
        finish();
    }
}
