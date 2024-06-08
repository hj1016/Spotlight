package com.example.spotlight.network.Service;

import android.view.View;
import android.widget.TextView;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.FeedHitsResponse;
import com.example.spotlight.network.Util.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedService {
    private ApiService apiService;

    public FeedService(ApiService apiService) {
        this.apiService = apiService;
    }

    // 사용자 조회수 조회
    public void getUserHits(int feedId, final UserHitsCallback callback) {
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        apiService.getFeedHits(feedId).enqueue(new Callback<FeedHitsResponse>() {
            @Override
            public void onResponse(Call<FeedHitsResponse> call, Response<FeedHitsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getHits_user());
                } else {
                    callback.onError("Failed to get user hits");
                }
            }

            @Override
            public void onFailure(Call<FeedHitsResponse> call, Throwable t) {
                callback.onError("An error occurred: " + t.getMessage());
            }
        });
    }

    // 리크루터 조회수 조회
    public void getRecruiterHits(int feedId, final RecruiterHitsCallback callback) {
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        apiService.getFeedHits(feedId).enqueue(new Callback<FeedHitsResponse>() {
            @Override
            public void onResponse(Call<FeedHitsResponse> call, Response<FeedHitsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getHits_recruiter());
                } else {
                    callback.onError("Failed to get recruiter hits");
                }
            }

            @Override
            public void onFailure(Call<FeedHitsResponse> call, Throwable t) {
                callback.onError("An error occurred: " + t.getMessage());
            }
        });
    }

    // 조회수 반영 메서드
    public void updateHits(int feedId, final View generalView, final View recruiterView) {
        String userType = TokenManager.getRole();
        if ("NORMAL".equals(userType)) {
            getUserHits(feedId, new UserHitsCallback() {
                @Override
                public void onSuccess(int hits) {
                    // 조회수 업데이트 성공
                    ((TextView) generalView.findViewById(R.id.item_detail_general_view)).setText(String.valueOf(hits));
                    System.out.println("User Hits: " + hits);
                }

                @Override
                public void onError(String errorMessage) {
                    // 조회수 업데이트 실패
                    System.err.println("Error: " + errorMessage);
                }
            });
        } else if ("RECRUITER".equals(userType)) {
            getRecruiterHits(feedId, new RecruiterHitsCallback() {
                @Override
                public void onSuccess(int hits) {
                    // 조회수 업데이트 성공
                    ((TextView) recruiterView.findViewById(R.id.item_detail_recruiter_view)).setText(String.valueOf(hits));
                    System.out.println("Recruiter Hits: " + hits);
                }

                @Override
                public void onError(String errorMessage) {
                    // 조회수 업데이트 실패
                    System.err.println("Error: " + errorMessage);
                }
            });
        }
    }

    public interface UserHitsCallback {
        void onSuccess(int hits);

        void onError(String errorMessage);
    }

    public interface RecruiterHitsCallback {
        void onSuccess(int hits);

        void onError(String errorMessage);
    }
}