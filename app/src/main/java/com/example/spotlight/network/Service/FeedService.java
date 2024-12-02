package com.example.spotlight.network.Service;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.DTO.FeedDTO;
import com.example.spotlight.network.Response.FeedResponse;
import com.example.spotlight.network.Util.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedService {
    private ApiService apiService;

    public FeedService(ApiService apiService) {
        this.apiService = apiService;
    }

    // 조회수 반영 메서드
    public void updateHits(int feedId, final View generalView, final View recruiterView) {
        String userType = TokenManager.getRole(); // 사용자 역할 가져오기 (NORMAL, STUDENT, RECRUITER)

        incrementFeedHits(feedId, new IncrementHitsCallback() {
            @Override
            public void onSuccess(int updatedHitsUser, int updatedHitsRecruiter) {
                if ("NORMAL".equals(userType) || "STUDENT".equals(userType)) {
                    // NORMAL 또는 STUDENT 조회수 업데이트
                    ((TextView) generalView.findViewById(R.id.item_detail_general_view))
                            .setText(String.valueOf(updatedHitsUser));
                    Log.d("FeedService", "User/Student Hits Updated: " + updatedHitsUser);
                } else if ("RECRUITER".equals(userType)) {
                    // RECRUITER 조회수 업데이트
                    ((TextView) recruiterView.findViewById(R.id.item_detail_recruiter_view))
                            .setText(String.valueOf(updatedHitsRecruiter));
                    Log.d("FeedService", "Recruiter Hits Updated: " + updatedHitsRecruiter);
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("FeedService", "Failed to update hits: " + errorMessage);
            }
        });
    }

    // 조회수 증가 API 호출
    public void incrementFeedHits(int feedId, IncrementHitsCallback callback) {
        Call<FeedDTO> call = apiService.incrementFeedHits((long) feedId);

        call.enqueue(new Callback<FeedDTO>() {
            @Override
            public void onResponse(Call<FeedDTO> call, Response<FeedDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // 조회수 정보만 업데이트
                    int updatedHitsUser = response.body().getHitsUser();
                    int updatedHitsRecruiter = response.body().getHitsRecruiter();

                    // Callback으로 조회수 정보 전달
                    callback.onSuccess(updatedHitsUser, updatedHitsRecruiter);
                } else {
                    String errorMessage = "Server response error: " + response.code();
                    callback.onError(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<FeedDTO> call, Throwable t) {
                String errorMessage = "Network error: " + t.getMessage();
                callback.onError(errorMessage);
            }
        });
    }

    public interface IncrementHitsCallback {
        void onSuccess(int updatedHitsUser, int updatedHitsRecruiter);
        void onError(String errorMessage);
    }

}