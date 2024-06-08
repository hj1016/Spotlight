package com.example.spotlight.network.Util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.spotlight.R;

public class LoadingUtil {

    private static View loadingView;

    // 로딩 애니메이션 시작 메소드
    public static void showLoading(Activity activity) {
        if (loadingView == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            loadingView = inflater.inflate(R.layout.loading_layout, null);

            // FrameLayout.LayoutParams를 사용하여 로딩 뷰를 전체 화면에 추가
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            activity.addContentView(loadingView, params);
        }

        loadingView.setVisibility(View.VISIBLE);
    }

    // 로딩 애니메이션 종료 메소드
    public static void hideLoading() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }
}