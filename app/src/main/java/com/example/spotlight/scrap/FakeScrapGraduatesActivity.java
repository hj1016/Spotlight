package com.example.spotlight.scrap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.R;

public class FakeScrapGraduatesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_scrap_graduates);

    }
    public void onBackClicked(View view) {
        finish();
    }

}
