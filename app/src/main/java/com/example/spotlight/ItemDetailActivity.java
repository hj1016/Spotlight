package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Arrays;
import java.util.List;
import com.google.android.flexbox.FlexboxLayout;

import android.widget.ImageView;
import android.widget.TextView;
import android.util.TypedValue;
import android.view.Gravity;



public class ItemDetailActivity extends AppCompatActivity {

    private ViewPager2 viewPagerImages;
    private ImageSliderAdapter adapter;

    private boolean isScrapped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        // 이미지 URL로 ViewPager2 초기화
        List<String> images = Arrays.asList(
                "https://example.com/image1.jpg", // 실제 이미지 URL로 바꿔야 함
                "https://example.com/image2.jpg",
                "https://example.com/image3.jpg",
                "https://example.com/image4.jpg",
                "https://example.com/image5.jpg",
                "https://example.com/image6.jpg",
                "https://example.com/image7.jpg",
                "https://example.com/image8.jpg",
                "https://example.com/image9.jpg",
                "https://example.com/image10.jpg"
        );

        viewPagerImages = findViewById(R.id.viewPagerImages);
        adapter = new ImageSliderAdapter(images);
        viewPagerImages.setAdapter(adapter);
    }

    public void addHashtags(List<String> hashtags, FlexboxLayout flexboxLayout) {
        for (String hashtag : hashtags) {
            TextView textView = new TextView(this);
            textView.setText("#" + hashtag);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setBackground(getResources().getDrawable(R.drawable.hashtag_box));
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(20, 10, 20, 10);

            FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(10, 5, 10, 5);
            textView.setLayoutParams(layoutParams);

            flexboxLayout.addView(textView);
        }
    }


    public void onBackClicked(View view) {
        finish();
    }

    public void toggleScrap(View view) {
        ImageView scrapButton = (ImageView) view;
        isScrapped = !isScrapped; // Toggle the state
        scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);
    }
}
