package com.example.spotlight.network.Service;

import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.DeleteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteService {
    private ApiService apiService;

    public DeleteService(ApiService apiService) {
        this.apiService = apiService;
    }

    public void deleteFeed(int feedId, final DeleteCallback callback) {
        apiService.deleteFeed(feedId).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("게시물이 성공적으로 삭제되었습니다!");
                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                callback.onError("오류가 발생했습니다: " + t.getMessage());
            }
        });
    }

    public interface DeleteCallback {
        void onSuccess(DeleteResponse response);
        void onError(String errorMessage);
    }
}