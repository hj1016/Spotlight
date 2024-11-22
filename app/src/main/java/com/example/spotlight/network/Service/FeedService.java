package com.example.spotlight.network.Service;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiService;
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
            public void onSuccess(FeedResponse.Post post) {
                if ("NORMAL".equals(userType) || "STUDENT".equals(userType)) {
                    // NORMAL 또는 STUDENT 조회수 업데이트
                    int userHits = post.getHitsUser();
                    ((TextView) generalView.findViewById(R.id.item_detail_general_view))
                            .setText(String.valueOf(userHits));
                    Log.d("FeedService", "User/Student Hits Updated: " + userHits);
                } else if ("RECRUITER".equals(userType)) {
                    // RECRUITER 조회수 업데이트
                    int recruiterHits = post.getHitsRecruiter();
                    ((TextView) recruiterView.findViewById(R.id.item_detail_recruiter_view))
                            .setText(String.valueOf(recruiterHits));
                    Log.d("FeedService", "Recruiter Hits Updated: " + recruiterHits);
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
        Call<FeedResponse> call = apiService.incrementFeedHits((long) feedId);

        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FeedResponse.Post post = response.body().getPost();
                    callback.onSuccess(post);
                } else {
                    String errorMessage = "Server response error: " + response.code();
                    callback.onError(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                String errorMessage = "Network error: " + t.getMessage();
                callback.onError(errorMessage);
            }
        });
    }

    public interface IncrementHitsCallback {
        void onSuccess(FeedResponse.Post post);

        void onError(String errorMessage);
    }
}