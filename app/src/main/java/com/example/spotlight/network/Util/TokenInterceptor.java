package com.example.spotlight.network.Util;

import androidx.annotation.NonNull;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
public class TokenInterceptor implements Interceptor {

    public TokenInterceptor() {
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String token = TokenManager.getToken();

        if (token.isEmpty()) {
            return chain.proceed(originalRequest);
        }

        Request.Builder builder = originalRequest.newBuilder();
        if (requiresToken(originalRequest.url().toString())) {
            builder.addHeader("Authorization", "Bearer " + token);
        }

        Request modifiedRequest = builder.build();
        return chain.proceed(modifiedRequest);
    }

    private boolean requiresToken(String url) {
        return !isTokenNotRequired(url);
    }

    private boolean isTokenNotRequired(String url) {
        return url.contains("/api/user/register")
                && !url.contains("/api/user/existusername")
                && !url.contains("/api/user/validate-password")
                && !url.contains("/api/user/send-email-verification")
                && !url.contains("/api/user/verify-email-verification")
                && !url.contains("/api/user/upload-student-certificate")
                && !url.contains("/api/user/upload-recruiter-certificate")
                && !url.contains("/api/user/login");
    }

}
