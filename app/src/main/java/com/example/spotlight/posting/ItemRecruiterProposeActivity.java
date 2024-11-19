package com.example.spotlight.posting;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

import com.example.spotlight.recruiter.RecruiterProposeEditActivity;

public class ItemRecruiterProposeActivity extends AppCompatActivity {
    public void onEditClicked(View view) {
        Intent intent = new Intent(this, RecruiterProposeEditActivity.class);
        startActivity(intent);
    }
}
