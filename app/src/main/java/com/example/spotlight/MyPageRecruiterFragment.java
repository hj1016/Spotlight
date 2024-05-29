package com.example.spotlight;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;

public class MyPageRecruiterFragment extends Fragment {

    public MyPageRecruiterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mypage_recruiter, container, false);

        view.findViewById(R.id.mypage_recruiter_profile).setOnClickListener(this::onProfileClicked);
        view.findViewById(R.id.mypage_recruiter_scrap_post).setOnClickListener(this::onScrapPostingClicked);
        view.findViewById(R.id.mypage_recruiter_scrap_person).setOnClickListener(this::onScrapGraduatesClicked);
        view.findViewById(R.id.mypage_recruiter_recruit_post).setOnClickListener(this::onProposeClicked);

        return view;
    }

    private void onProfileClicked(View view) {
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

    private void onScrapPostingClicked(View view) {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), ScrapProjectActivity.class);
            startActivity(intent);
        }
    }

    private void onScrapGraduatesClicked(View view) {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), ScrapGraduatesActivity.class);
            startActivity(intent);
        }
    }

    private void onProposeClicked(View view) {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), RecruiterProposeActivity.class);
            startActivity(intent);
        }
    }
}
