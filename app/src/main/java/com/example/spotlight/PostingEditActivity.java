package com.example.spotlight;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PostingEditActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_edit);
    }
    public void onDeleteClicked(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_delete, null);

        // 다이얼로그 빌더 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        // 다이얼로그 생성 및 설정
        AlertDialog dialog = builder.create();

        // "예" 버튼 클릭 리스너 설정
        dialogView.findViewById(R.id.dialog_delete_ok).setOnClickListener(v -> {
            // 삭제 동작 수행
            // TODO: 여기에 삭제 동작 코드를 추가하세요.
            dialog.dismiss();
        });

        // "아니오" 버튼 클릭 리스너 설정
        dialogView.findViewById(R.id.dialog_delete_cancel).setOnClickListener(v -> dialog.dismiss());

        // 다이얼로그 표시
        dialog.show();
    }


    public void onBackClicked(View view) {
        finish();
    }

    public void onCompletePostingEditClicked(View view) {
        finish();
    }
}
