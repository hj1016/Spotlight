package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class ItemPostActivity extends AppCompatActivity {
    public void onOpenClicked(View view) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        startActivity(intent);
    }
}
