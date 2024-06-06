package com.example.spotlight;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.UserProfileResponse;
import com.example.spotlight.network.Util.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileGraduatesActivity extends AppCompatActivity {
    private TextView textViewUsername;
    private TextView textViewId;
    private TextView textViewSchool;
    private TextView textViewMajor;
    private String username;
    private String id;
    private String school;
    private String major;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_profile_graduates);

        textViewUsername = findViewById(R.id.profile_graduates_user_name);
        textViewId = findViewById(R.id.profile_graduates_ID_text);
        textViewSchool = findViewById(R.id.profile_graduates_school_text);
        textViewMajor = findViewById(R.id.profile_graduates_major_text);

        fetchDataFromApi();

    }

    // 참고할 부분 1) sharedPreferences을 감싼 TokenManager로 활용함
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
        Intent intent = new Intent(this, ProfileGraduatesEditActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("id", id);
        intent.putExtra("school", school);
        intent.putExtra("major", major);
        startActivity(intent);
    }

    // 참고할 부분 2) Token을 header에 포함시켜 사용하는 api 호출
    private void fetchDataFromApi() {
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<UserProfileResponse> call = apiService.getProfile();
        call.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserProfileResponse userProfileResponse = response.body();
                    username = userProfileResponse.getUsername();
                    id = userProfileResponse.getId();
                    school = userProfileResponse.getSchool();
                    major = userProfileResponse.getMajor();
                    textViewUsername.setText(username);
                    textViewId.setText(id);
                    textViewSchool.setText(school);
                    textViewMajor.setText(major);
                } else {
                    Toast.makeText(ProfileGraduatesActivity.this, "데이터를 가져오는 데에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                Toast.makeText(ProfileGraduatesActivity.this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
