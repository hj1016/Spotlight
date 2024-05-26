package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class NewPostingMemberActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_posting_member_invite);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, NewPostingActivity.class);
        startActivity(intent);
    }

    public void onInviteClicked(View view) {
        Intent intent = new Intent(this, NewPostingActivity.class);
        startActivity(intent);
    }
}
