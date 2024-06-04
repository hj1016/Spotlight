package com.example.spotlight;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import androidx.annotation.Nullable;

import com.example.spotlight.network.Util.TokenManager;

public class MyPageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mypage_general, container, false);

        // Set up event handlers
        view.findViewById(R.id.mypage_alarm).setOnClickListener(this::onAlarmClicked);
        view.findViewById(R.id.mypage_profile).setOnClickListener(this::onProfileClicked);
        view.findViewById(R.id.mypage_posting_manage).setOnClickListener(this::onManagePostingClicked);
        view.findViewById(R.id.mypage_portfolio).setOnClickListener(this::onPortfolioClicked);
        view.findViewById(R.id.mypage_scrap_post).setOnClickListener(this::onScrapPostingClicked);
        view.findViewById(R.id.mypage_recruit_post).setOnClickListener(this::onProposeClicked);

        return view;
    }
    public void onAlarmClicked(View view) {
        if (getActivity() != null) {
            SharedPreferences prefs = getActivity().getSharedPreferences("Alarm", Context.MODE_PRIVATE);
            String userType = prefs.getString("userType", "general");

            Intent intent;
            if ("graduates".equals(userType)) {
                intent = new Intent(getActivity(), AlarmActivity.class);
            } else {
                intent = new Intent(getActivity(), AlarmGeneralActivity.class);
            }

            startActivity(intent);
        }
    }

    public void onProfileClicked(View view) {
        if (getActivity() != null) {
            SharedPreferences prefs = getActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
            String userType = prefs.getString("userType", "general");

            Intent intent;
            if ("graduates".equals(userType)) {
                intent = new Intent(getActivity(), ProfileGraduatesActivity.class);
            } else {
                intent = new Intent(getActivity(), ProfileGeneralActivity.class);
            }

            startActivity(intent);
        }
    }

    public void onManagePostingClicked(View view) {
        if (getActivity() != null) {
            TokenManager tokenManager = new TokenManager(getActivity());
            String userType = tokenManager.getRole();

            Intent intent;
            if ("STUDENT".equals(userType)) {
                intent = new Intent(getActivity(), ManagePostingActivity.class);
            } else {
                intent = new Intent(getActivity(), ManagePostingGeneralActivity.class);
            }

            startActivity(intent);
        }
    }

    public void onPortfolioClicked(View view) {
        if (getActivity() != null) {
            SharedPreferences prefs = getActivity().getSharedPreferences("Portfolio", Context.MODE_PRIVATE);
            String userType = prefs.getString("userType", "general");

            Intent intent;
            if ("graduates".equals(userType)) {
                intent = new Intent(getActivity(), MyPagePortfolioActivity.class);
            } else {
                intent = new Intent(getActivity(), MyPageGeneralPortfolioActivity.class);
            }

            startActivity(intent);
        }
    }

    public void onScrapPostingClicked(View view) {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), ScrapProjectActivity.class);
            startActivity(intent);
        }
    }

    public void onProposeClicked(View view) {
        if (getActivity() != null) {
            SharedPreferences prefs = getActivity().getSharedPreferences("Propose", Context.MODE_PRIVATE);
            String userType = prefs.getString("userType", "general");

            Intent intent;
            if ("graduates".equals(userType)) {
                intent = new Intent(getActivity(), GraduatesProposeActivity.class);
            } else {
                intent = new Intent(getActivity(), GeneralProposeManageActivity.class);
            }

            startActivity(intent);
        }
    }
}


