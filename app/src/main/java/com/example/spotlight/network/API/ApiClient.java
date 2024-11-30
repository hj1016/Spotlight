package com.example.spotlight.network.API;

import com.example.spotlight.network.Util.TokenInterceptor;
import com.example.spotlight.network.Util.TokenManager;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiClient {
    // 안드로이드 - ec2 서버 연결된 거
    // private static final String BASE_URL = "http://13.49.215.3:8080";

    // 안드로이드 - 로컬 서버
    private static final String BASE_URL = "http://10.0.2.2:8080";

    private static Retrofit retrofit = null;
    private static Retrofit retrofitWithToken = null;
    private static Retrofit retrofitWithoutToken = null;
    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new TokenInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientWithToken() {
        if (retrofitWithToken == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer " + TokenManager.getToken());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            retrofitWithToken = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofitWithToken;
    }

    public static Retrofit getClientWithoutToken() {
        if (retrofitWithoutToken == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            retrofitWithoutToken = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofitWithoutToken;
    }

    public static ApiService getChatbotApiServiceWithToken() {
        return getClientWithToken().create(ApiService.class);
    }

    public static ApiService getChatbotApiServiceWithoutToken() {
        return getClientWithoutToken().create(ApiService.class);
    }

}
