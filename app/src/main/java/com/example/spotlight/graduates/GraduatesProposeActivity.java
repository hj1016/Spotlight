package com.example.spotlight.graduates;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.spotlight.posting.ItemDetailMemberRecruiterActivity;
import com.example.spotlight.R;
import com.example.spotlight.network.API.*;
import com.example.spotlight.network.Response.ProposalResponse;
import com.example.spotlight.network.Util.LoadingUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraduatesProposeActivity extends AppCompatActivity {
    private ApiService apiService;
    private RecyclerView recyclerView;
    private GraduatesProposeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graduates_propose_manage);
        LoadingUtil.showLoading(this);

        recyclerView = findViewById(R.id.recyclerView_graduates_propose_manage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiService = ApiClient.getClient().create(ApiService.class);

        Call<List<ProposalResponse>> call = apiService.getProposalsByStudent();
        call.enqueue(new Callback<List<ProposalResponse>>() {
            @Override
            public void onResponse(Call<List<ProposalResponse>> call, Response<List<ProposalResponse>> response) {
                if (response.isSuccessful()) {
                    List<ProposalResponse> proposals = response.body();

                    if (proposals != null && !proposals.isEmpty()) {
                        Log.d("GraduatesProposeActivity", "Proposals size: " + proposals.size());
                    } else {
                        Log.d("GraduatesProposeActivity", "No proposals available or null data.");
                    }

                    adapter = new GraduatesProposeAdapter(GraduatesProposeActivity.this, proposals);
                    recyclerView.setAdapter(adapter);
                    LoadingUtil.hideLoading();
                } else {
                    LoadingUtil.hideLoading();
                    Toast.makeText(GraduatesProposeActivity.this, "데이터 가져오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProposalResponse>> call, Throwable t) {
                LoadingUtil.hideLoading();
                if (t instanceof java.net.SocketTimeoutException) {
                    showError("서버 응답이 지연되고 있습니다. 잠시 후 다시 시도해 주세요.");
                } else {
                    showError("네트워크 오류가 발생했습니다. 인터넷 연결을 확인해 주세요.");
                }
                Log.e("API_CALL_FAILURE", "API call failed: " + t.getMessage(), t);
            }
        });
    }

    private void showError(String message) {
        Toast.makeText(GraduatesProposeActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onReceivedProposeClicked(View view) {
        Intent intent = new Intent(this, ItemDetailMemberRecruiterActivity.class);
        startActivity(intent);
    }
}