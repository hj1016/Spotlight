package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.RadioGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupStep5Activity extends AppCompatActivity {

    private RadioButton userSelec1, userSelec2;
    private RadioGroup radioGroupUserType;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_step5);

        // 라디오 버튼 및 라디오 그룹 초기화
        userSelec1 = findViewById(R.id.User_selec1);
        userSelec2 = findViewById(R.id.User_selec2);
        radioGroupUserType = findViewById(R.id.radioGroupUserType);
        sharedPreferences = getSharedPreferences("UserType", MODE_PRIVATE);
    }

    // "Continue" 이미지 클릭 시 로직 처리
    public void onContinueClicked(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int selectedId = radioGroupUserType.getCheckedRadioButtonId(); // Get the ID of the selected radio button
        if (selectedId == R.id.User_selec1) {
            editor.putString("Type", "Graduate");
            editor.apply();
            Intent intent = new Intent(this, GraduateStep1Activity.class);
            startActivity(intent);
        } else if (selectedId == R.id.User_selec2) {
            editor.putString("Type", "Recruiter");
            editor.apply();
            Intent intent = new Intent(this, RecruiterStep1Activity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "사용자 유형을 선택하세요", Toast.LENGTH_SHORT).show();
        }
    }

    // "Skip" 이미지 클릭 시 MainActivity로 이동
    public void onSkipClicked(View view) {


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
