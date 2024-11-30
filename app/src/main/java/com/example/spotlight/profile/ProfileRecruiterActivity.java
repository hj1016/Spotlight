package com.example.spotlight.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.example.spotlight.main.MainActivity;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.UserProfileResponse;
import com.example.spotlight.network.Util.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRecruiterActivity extends AppCompatActivity {
    private TextView textViewUsername;
    private TextView textViewId;
    private TextView textViewCompany;
    private ImageView imageViewProfile;
    private String username;
    private String id;
    private String company;
    private String profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_profile_recruiter);

        textViewUsername = findViewById(R.id.profile_recruiter_user_name);
        textViewId = findViewById(R.id.profile_recruiter_ID_text);
        textViewCompany = findViewById(R.id.profile_recruiter_company_text);
        imageViewProfile = findViewById(R.id.profile_recruiter_user_image);

        fetchDataFromApi();
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

    private void fetchDataFromApi() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<UserProfileResponse> call = apiService.getProfile();
        call.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserProfileResponse userProfileResponse = response.body();

                    Log.d("userProfileResponse", userProfileResponse.toString());
                    username = userProfileResponse.getName();
                    id = userProfileResponse.getUsername();
                    company = userProfileResponse.getCompany(); // 회사 정보 받기
                    profileImg = userProfileResponse.getProfileImageUrl();

                    TokenManager.setName(username);
                    TokenManager.setUsername(id);
                    TokenManager.setCompany(company);
                    if (profileImg == null) {
                        profileImg = "";
                    }
                    TokenManager.setProfileImage(profileImg);

                    textViewUsername.setText(username);
                    textViewId.setText(id);
                    textViewCompany.setText(company);

                    if (profileImg != null && !profileImg.isEmpty()) {
                        Glide.with(ProfileRecruiterActivity.this)
                                .load(profileImg)
                                .circleCrop()
                                .into(imageViewProfile);
                    }
                } else {
                    Log.d("response", response.toString());
                    Toast.makeText(ProfileRecruiterActivity.this, "데이터를 가져오는 데에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                Toast.makeText(ProfileRecruiterActivity.this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
