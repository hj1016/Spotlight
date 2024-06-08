package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class MyPageRecruiterActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_recruiter);

//        ImageView imageViewProfile = findViewById(R.id.mypage_recruiter_image);
//        TextView username = findViewById(R.id.mypage_recruiter_name);
//        String usernameStr = TokenManager.getUsername();
//        String profileImg = TokenManager.getProfileImage();
//        username.setText(usernameStr);
//        if(profileImg != null && !profileImg.isEmpty()) {
//            Glide.with(this)
//                    .load(profileImg)
//                    .into(imageViewProfile);
//        }
    }

    public void onProfileClicked(View view) {
        Intent intent = new Intent(this, ProfileRecruiterActivity.class);
        startActivity(intent);
    }

    public void onScrapPostingClicked(View view) {
        Intent intent = new Intent(this, ScrapProjectActivity.class);
        startActivity(intent);
    }

    public void onScrapGraduatesClicked(View view) {
        Intent intent = new Intent(this, ScrapGraduatesActivity.class);
        startActivity(intent);
    }

    public void onProposeClicked(View view) {
        Intent intent = new Intent(this, RecruiterProposeManageActivity.class);
        startActivity(intent);
    }
}
