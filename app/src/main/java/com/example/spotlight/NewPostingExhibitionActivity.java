package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class NewPostingExhibitionActivity extends AppCompatActivity {

    private EditText locationEditText;
    private EditText scheduleEditText;
    private EditText timeEditText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_posting_exhibition_info);

        locationEditText = findViewById(R.id.new_posting_exhibition_location_text);
        scheduleEditText = findViewById(R.id.new_posting_exhibition_date_text);
        timeEditText = findViewById(R.id.new_posting_exhibition_time_text);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, NewPostingActivity.class);
        startActivity(intent);
    }

    public void onCompleteExhibitionClicked(View view) {
        String location = locationEditText.getText().toString();
        String schedule = scheduleEditText.getText().toString();
        String time = timeEditText.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("location", location);
        resultIntent.putExtra("schedule", schedule);
        resultIntent.putExtra("time", time);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
