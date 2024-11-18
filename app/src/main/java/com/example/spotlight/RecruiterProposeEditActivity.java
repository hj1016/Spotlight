package com.example.spotlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Request.ProposalRequest;
import com.example.spotlight.network.Response.ApiResponse;
import com.example.spotlight.network.Util.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecruiterProposeEditActivity extends AppCompatActivity {

    private EditText companyText, jobText, descriptionText, contactText;
    private int proposalId;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruiter_propose_edit);

        Intent intent = getIntent();
        proposalId = intent.getIntExtra("proposalId", -1);

        companyText = findViewById(R.id.recruiter_propose_edit_company_text);
        jobText = findViewById(R.id.recruiter_propose_edit_job_text);
        descriptionText = findViewById(R.id.recruiter_propose_edit_description_text);
        contactText = findViewById(R.id.recruiter_propose_edit_contact_text);
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onProposeEditClicked(View view) {
        String company = companyText.getText().toString().trim();
        String job = jobText.getText().toString().trim();
        String description = descriptionText.getText().toString().trim();
        String contact = contactText.getText().toString().trim();

        if (company.isEmpty() || job.isEmpty() || description.isEmpty() || contact.isEmpty()) {
            Toast.makeText(this, "모든 필드를 채워주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        ProposalRequest updateRequest = new ProposalRequest(company, job, contact, description);

        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        String token = TokenManager.getToken();

        Call<ApiResponse> call = apiService.updateProposal(proposalId, updateRequest, token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RecruiterProposeEditActivity.this, "보낸 제안서를 성공적으로 수정했습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RecruiterProposeEditActivity.this, "보낸 제안서를 수정하는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(RecruiterProposeEditActivity.this, "네트워크 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }

     */
}