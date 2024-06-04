package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class NewPostingMemberActivity extends AppCompatActivity {

    private EditText memberIdEditText;
    private EditText roleEditText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_posting_member_invite);

        memberIdEditText = findViewById(R.id.new_posting_member_ID_text);
        roleEditText = findViewById(R.id.new_posting_member_role_text);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, NewPostingActivity.class);
        startActivity(intent);
    }

    public void onInviteMemberClicked(View view) {
        String memberId = memberIdEditText.getText().toString();
        String role = roleEditText.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("memberId", memberId);
        resultIntent.putExtra("role", role);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
