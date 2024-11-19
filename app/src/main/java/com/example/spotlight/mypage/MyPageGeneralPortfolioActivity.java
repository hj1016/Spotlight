package com.example.spotlight.mypage;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

import com.example.spotlight.R;

public class MyPageGeneralPortfolioActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_portfolio_not_graduates);
    }

    public void onBackClicked(View view) {
        finish();
    }
}
