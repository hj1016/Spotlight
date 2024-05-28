package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class ItemRecruiterProposeActivity extends AppCompatActivity {
    public void onEditClicked(View view) {
        Intent intent = new Intent(this, RecruiterProposeEditActivity.class);
        startActivity(intent);
    }
}
