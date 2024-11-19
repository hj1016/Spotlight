package com.example.spotlight.alarm;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

import com.example.spotlight.R;

public class AlarmGeneralActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_not_graduates);
    }

    public void onBackClicked(View view) {
        finish();
    }
}
