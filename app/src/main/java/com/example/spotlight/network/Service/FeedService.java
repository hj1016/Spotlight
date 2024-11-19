package com.example.spotlight.network.Service;

import com.example.spotlight.network.API.ApiService;

public class FeedService {
    private ApiService apiService;

    public FeedService(ApiService apiService) {
        this.apiService = apiService;
    }

    /*
    // 사용자 조회수 조회
    public void getUserHits(int feedId, final UserHitsCallback callback) {
        String accessToken = TokenManager.getToken(); // 토큰 가져오기
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        apiService.getFeedHits(feedId, accessToken).enqueue(new Callback<FeedDTO>() {
            @Override
            public void onResponse(Call<FeedDTO> call, Response<FeedDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getHitsUser());
                } else {
                    callback.onError("Failed to get user hits");
                }
            }

            @Override
            public void onFailure(Call<FeedDTO> call, Throwable t) {
                callback.onError("An error occurred: " + t.getMessage());
            }
        });
    }

    // 리크루터 조회수 조회
    public void getRecruiterHits(int feedId, final RecruiterHitsCallback callback) {
        String accessToken = TokenManager.getToken(); // 토큰 가져오기
        ApiService apiService = ApiClient.getClientWithToken().create(ApiService.class);
        apiService.getFeedHits(feedId, accessToken).enqueue(new Callback<FeedDTO>() {
            @Override
            public void onResponse(Call<FeedDTO> call, Response<FeedDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getHitsRecruiter());
                } else {
                    callback.onError("Failed to get recruiter hits");
                }
            }

            @Override
            public void onFailure(Call<FeedDTO> call, Throwable t) {
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

     */
}