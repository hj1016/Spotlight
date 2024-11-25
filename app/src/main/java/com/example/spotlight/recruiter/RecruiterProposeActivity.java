package com.example.spotlight.recruiter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Request.ProposalRequest;
import com.example.spotlight.network.Response.ProposalResponse;
import com.example.spotlight.network.Util.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecruiterProposeActivity extends AppCompatActivity {
    private EditText companyText, jobText, descriptionText, contactText;
    private Long studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruiter_propose);

        companyText = findViewById(R.id.recruiter_propose_company_text);
        jobText = findViewById(R.id.recruiter_propose_job_text);
        descriptionText = findViewById(R.id.recruiter_propose_description_text);
        contactText = findViewById(R.id.recruiter_propose_contact_text);

        // Intent로 전달된 studentId 가져오기
        studentId = getIntent().getLongExtra("studentId", -1L);
        if (studentId == -1L) {
            Toast.makeText(this, "유효하지 않은 학생 정보입니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

        initializeRecruiterData();
    }

    private void initializeRecruiterData() {
        String role = TokenManager.getRole();
        String company = TokenManager.getCompany();

        if ("RECRUITER".equals(role)) {
            companyText.setText(company);
        } else {
            Toast.makeText(this, "리쿠르터 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void onProposeCompleteClicked(View view) {
        String company = companyText.getText().toString().trim();
        String job = jobText.getText().toString().trim();
        String description = descriptionText.getText().toString().trim();
        String contact = contactText.getText().toString().trim();

        // 입력값 검증
        if (company.isEmpty() || job.isEmpty() || description.isEmpty() || contact.isEmpty()) {
            Toast.makeText(this, "모든 필드를 채워주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // ProposalRequest 생성
        ProposalRequest proposalRequest = new ProposalRequest(job, contact, description, studentId);

        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        Call<ProposalResponse> call = apiService.createProposal(proposalRequest);

        // API 호출
        call.enqueue(new Callback<ProposalResponse>() {
            @Override
            public void onResponse(Call<ProposalResponse> call, Response<ProposalResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RecruiterProposeActivity.this, "제안서를 성공적으로 보냈습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RecruiterProposeActivity.this, "제안서 전송에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProposalResponse> call, Throwable t) {
                Toast.makeText(RecruiterProposeActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}