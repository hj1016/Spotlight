package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.UserProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileGraduatesEditActivity extends AppCompatActivity {
    private TextView textViewUsername;
    private TextView textViewId;
    private TextView textViewSchool;
    private TextView textViewMajor;
    private ApiClient apiClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_profile_graduates_edit);

        String username = getIntent().getStringExtra("username");
        String id = getIntent().getStringExtra("id");
        String school = getIntent().getStringExtra("school");
        String major = getIntent().getStringExtra("major");

        textViewUsername = findViewById(R.id.profile_graduates_edit_user_name);
        textViewId = findViewById(R.id.profile_graduates_edit_ID_text);
        textViewSchool = findViewById(R.id.profile_graduates_edit_school_text);
        textViewMajor = findViewById(R.id.profile_graduates_edit_major_text);

        textViewUsername.setText(username);
        textViewId.setText(id);
        textViewSchool.setText(school);
        textViewMajor.setText(major);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, ProfileGraduatesActivity.class);
        startActivity(intent);
    }

    public void onCompleteClicked(View view) {
        Intent intent = new Intent(this, ProfileGraduatesActivity.class);
        startActivity(intent);
    }
}
