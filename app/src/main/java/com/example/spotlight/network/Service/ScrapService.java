package com.example.spotlight.network.Service;

import com.example.spotlight.network.API.ApiService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScrapService {
    private ApiService apiService;

    public ScrapService(ApiService apiService) {
        this.apiService = apiService;
    }

    public void scrapMember(Integer studentId, Callback<Map<String, Object>> callback) {
        Call<Map<String, Object>> call = apiService.scrapMember(studentId);
        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call, response);
                } else {
                    callback.onFailure(call, new Exception("Failed to scrap member"));
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}