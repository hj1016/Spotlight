package com.example.spotlight.profile;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.spotlight.R;
import com.example.spotlight.main.MainActivity;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Util.TokenManager;

public class ProfileRecruiterActivity extends AppCompatActivity {
    private TextView textViewUsername;
    private TextView textViewId;
    private TextView textViewCompany;
    private ImageView imageViewProfile;
    private Uri imageUri;
    private ApiService apiService;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_profile_recruiter);

        textViewUsername = findViewById(R.id.profile_recruiter_user_name);
        textViewId = findViewById(R.id.profile_recruiter_ID_text);
        textViewCompany = findViewById(R.id.profile_recruiter_company_text);
        imageViewProfile = findViewById(R.id.profile_recruiter_user_image);

        String id = TokenManager.getUsername();
        String company = TokenManager.getCompany();
        String profileImg = TokenManager.getProfileImage();
        String username = TokenManager.getName();

        textViewUsername.setText(username);
        textViewId.setText(id);
        textViewCompany.setText(company);
        if(profileImg != null && !profileImg.isEmpty()) {
            RequestOptions requestOptions = new RequestOptions()
                    .override(100, 100)
                    .circleCrop();

            Glide.with(ProfileRecruiterActivity.this)
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
        Intent intent = new Intent(this, ProfileRecruiterEditActivity.class);
        startActivity(intent);
    }
}
