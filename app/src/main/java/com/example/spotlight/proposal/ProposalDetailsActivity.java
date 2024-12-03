package com.example.spotlight.proposal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.ProposalResponse;
import com.example.spotlight.network.Util.TokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProposalDetailsActivity extends AppCompatActivity {
    private TextView companyTextView, jobTextView, descriptionTextView, contactTextView, nameTextView, majorTextView;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_details);

        // View 초기화
        companyTextView = findViewById(R.id.details_company);
        jobTextView = findViewById(R.id.details_job);
        descriptionTextView = findViewById(R.id.details_description);
        contactTextView = findViewById(R.id.details_contact);
        nameTextView = findViewById(R.id.details_graduates_name);
        majorTextView = findViewById(R.id.details_graduates_major);

        long proposalId = getIntent().getLongExtra("proposalId", -1);
        Log.d("ProposalDetails", "Received proposalId: " + proposalId);

        if (proposalId != -1) {
            fetchProposalDetails(proposalId);
        } else {
            Toast.makeText(this, "잘못된 데이터입니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void fetchProposalDetails(long proposalId) {
        boolean isStudent = isUserStudent();
        apiService = ApiClient.getClient().create(ApiService.class);

        Call<ProposalResponse> call = apiService.getProposalDetails(proposalId, isStudent);
        call.enqueue(new Callback<ProposalResponse>() {
            @Override
            public void onResponse(Call<ProposalResponse> call, Response<ProposalResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProposalResponse proposal = response.body();
                    updateUI(proposal);
                } else {
                    Toast.makeText(ProposalDetailsActivity.this, "데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProposalResponse> call, Throwable t) {
                Toast.makeText(ProposalDetailsActivity.this, "네트워크 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isUserStudent() {
        String userRole = TokenManager.getRole();
        return "STUDENT".equals(userRole);
    }

    private void updateUI(ProposalResponse proposal) {
        if (proposal == null) {
            Toast.makeText(this, "잘못된 데이터입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        companyTextView.setText(proposal.getRecruiter().getCompany());
        jobTextView.setText(proposal.getJob());
        descriptionTextView.setText(proposal.getDescription());
        contactTextView.setText(proposal.getContact());
        nameTextView.setText(proposal.getStudent().getName());
        majorTextView.setText(proposal.getStudent().getSchool());
    }

    // 뒤로 가기 버튼 클릭 처리
    public void onBackClicked(View view) {
        finish();
    }
}