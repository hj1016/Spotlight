package com.example.spotlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_graduates);
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onInviteNoClicked(View view) {
        showNoDialog();
    }

    public void onInviteOkClicked(View view) {
        showAcceptDialog();
    }

    private void showNoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_no, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        ImageButton confirmButton = dialogView.findViewById(R.id.dialog_no_ok);
        ImageButton cancelButton = dialogView.findViewById(R.id.dialog_no_cancel);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 여기서 거절 동작을 처리
                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showAcceptDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_ok, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        ImageButton confirmButton = dialogView.findViewById(R.id.dialog_invite_ok);
        ImageButton cancelButton = dialogView.findViewById(R.id.dialog_invite_no);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 여기서 수락 동작을 처리
                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
