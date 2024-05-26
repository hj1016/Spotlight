package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class MyPageGeneralPortfolioActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_portfolio_not_graduates);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, MyPageActivity.class);
        startActivity(intent);
    }
}
