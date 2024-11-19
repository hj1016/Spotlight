package com.example.spotlight.recruiter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.ApiResponse;
import com.example.spotlight.network.DTO.ProposalDTO;
import com.example.spotlight.network.Util.TokenManager;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecruiterProposeActivity extends AppCompatActivity {
    private EditText companyText, jobText, descriptionText, contactText;
    private ProposalDTO proposal;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruiter_propose);

        ProposalDTO proposal = new ProposalDTO();

        companyText = findViewById(R.id.recruiter_propose_company_text);
        jobText = findViewById(R.id.recruiter_propose_job_text);
        descriptionText = findViewById(R.id.recruiter_propose_description_text);
        contactText = findViewById(R.id.recruiter_propose_contact_text);
    }

    public void onProposeCompleteClicked(View view) {
        String company = companyText.getText().toString().trim();
        String job = jobText.getText().toString().trim();
        String description = descriptionText.getText().toString().trim();
        String contact = contactText.getText().toString().trim();

        if (company.isEmpty() || job.isEmpty() || description.isEmpty() || contact.isEmpty()) {
            Toast.makeText(this, "모든 필드를 채워주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        proposal.setCompany(company);
        proposal.setJob(job);
        proposal.setPhoneNumber(contact);
        proposal.setDescription(description);
        proposal.setUserId(TokenManager.getUserId());
        proposal.setCreatedDate(LocalDateTime.now());

        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        String token = TokenManager.getToken();

        Call<ApiResponse> call = apiService.createProposal(token, proposal);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    int proposalId = Integer.parseInt(response.body().getProposalId());
                    Intent intent = new Intent(RecruiterProposeActivity.this, RecruiterProposeEditActivity.class);
                    intent.putExtra("proposalId", proposalId);
                    startActivity(intent);

                    Toast.makeText(RecruiterProposeActivity.this, "제안서 보내기에 성공했습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RecruiterProposeActivity.this, "제안서 보내기에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(RecruiterProposeActivity.this, "네트워크 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }

     */
}