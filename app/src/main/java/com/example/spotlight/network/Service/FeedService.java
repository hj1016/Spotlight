package com.example.spotlight.network.Service;

import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.FeedHitsResponse;

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

    public interface UserHitsCallback {
        void onSuccess(int hits);

        void onError(String errorMessage);
    }

    public interface RecruiterHitsCallback {
        void onSuccess(int hits);

        void onError(String errorMessage);
    }
}