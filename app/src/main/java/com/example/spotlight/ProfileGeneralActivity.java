package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.spotlight.network.Util.TokenManager;

public class ProfileGeneralActivity extends AppCompatActivity {
    private TextView textViewUsername;
    private TextView textViewId;
    private ImageView imageViewProfile;
    private String username;
    private String id;
    private String profileImg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_profile_general);

        textViewUsername = findViewById(R.id.profile_general_user_name);
        textViewId = findViewById(R.id.profile_general_ID_text);
        imageViewProfile = findViewById(R.id.profile_general_user_image);

        id = TokenManager.getId();
        profileImg = TokenManager.getProfileImage();
        username = TokenManager.getUsername();

        textViewUsername.setText(username);
        textViewId.setText(id);
        if(profileImg != null && !profileImg.isEmpty()) {
            RequestOptions requestOptions = new RequestOptions()
                    .override(100, 100)
                    .circleCrop();

            Glide.with(ProfileGeneralActivity.this)
                    .load(profileImg)
                    .apply(requestOptions)
                    .into(imageViewProfile);
        }
    }

    public void onBackClicked(View view) {
        String userType = TokenManager.getRole();
        Intent intent = new Intent(this, MainActivity.class);
        if ("RECRUITER".equals(userType)) {
            intent.putExtra("Fragment", "MyPageRecruiterFragment");
        } else {
            intent.putExtra("Fragment", "MyPageFragment");
        }
        startActivity(intent);
        finish();
    }

    public void onEditClicked(View view) {
        Intent intent = new Intent(this, ProfileGeneralEditActivity.class);
        startActivity(intent);
    }
}
