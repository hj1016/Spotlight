package com.example.spotlight;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.DeleteResponse;
import com.example.spotlight.network.Service.DeleteService;

public class PostingEditActivity extends AppCompatActivity{

    private ApiService apiService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_edit);
    }

    // 게시물 삭제
    public void onDeleteClicked(View view, int feedId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("정말로 이 게시물을 삭제하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 게시물 삭제 요청
                        DeleteService deleteService = new DeleteService(apiService);
                        deleteService.deleteFeed(feedId, new DeleteService.DeleteCallback() {
                            @Override
                            public void onSuccess(DeleteResponse response) {
                                Toast.makeText(PostingEditActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                                // 게시물 삭제 후 현재 화면 종료
                                finish();
                            }

                            @Override
                            public void onError(String errorMessage) {
                                Toast.makeText(PostingEditActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onCompletePostingEditClicked(View view) {
        finish();
    }
}