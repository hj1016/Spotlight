package com.example.spotlight.profile;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.R;

public class PsjPortfolioActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.psj_portfolio);

        // 이미지 뷰 클릭 리스너 추가
        ImageView portfolioImageView = findViewById(R.id.fake_psj_portfolio);
        portfolioImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullScreenImage(R.drawable.psj_portfolio);
            }
        });
    }
    private void showFullScreenImage(int imageResource) {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_fullscreen_image);

        ImageView fullscreenImageView = dialog.findViewById(R.id.fullscreen_image);
        fullscreenImageView.setImageResource(imageResource);

        ImageView closeButton = dialog.findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void onBackClicked(View view) {
        finish();
    }
}
