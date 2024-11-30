package com.example.spotlight.recruiter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.ProposalResponse;
import com.example.spotlight.network.Util.LoadingUtil;
import com.example.spotlight.network.Util.TokenManager;
import com.example.spotlight.network.Util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecruiterProposeManageActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RecruiterProposeAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruiter_propose_manage);

        recyclerView = findViewById(R.id.recyclerView_recruiter_propose_manage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiService = ApiClient.getClientWithToken().create(ApiService.class);

        Call<List<ProposalResponse>> call = apiService.getProposalsByRecruiter();
        call.enqueue(new Callback<List<ProposalResponse>>() {
            @Override
            public void onResponse(Call<List<ProposalResponse>> call, Response<List<ProposalResponse>> response) {
                if (response.isSuccessful()) {
                    Utils.showJson(response.body());
                    List<ProposalResponse> proposals = response.body();

                    // RecyclerView에 데이터 설정
                    adapter = new RecruiterProposeAdapter(RecruiterProposeManageActivity.this, proposals);
                    recyclerView.setAdapter(adapter);
                    LoadingUtil.hideLoading();
                } else {
                    LoadingUtil.hideLoading();
                    Toast.makeText(RecruiterProposeManageActivity.this, "데이터 가져오기 실패", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<ProposalResponse>> call, Throwable t) {
                LoadingUtil.hideLoading();
                Log.e("API_CALL_FAILURE", "API call failed: " + t.getMessage(), t);
            }
        });
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onEditProposeClicked(View view) {
        Intent intent = new Intent(this, RecruiterProposeEditActivity.class);
        startActivity(intent);
    }
}