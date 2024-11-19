package com.example.spotlight.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.spotlight.recruiter.GeneralProposeManageActivity;
import com.example.spotlight.posting.ManagePostingActivity;
import com.example.spotlight.mypage.MyPageGeneralPortfolioActivity;
import com.example.spotlight.mypage.MyPagePortfolioActivity;
import com.example.spotlight.R;
import com.example.spotlight.scrap.ScrapProjectActivity;
import com.example.spotlight.alarm.AlarmActivity;
import com.example.spotlight.alarm.AlarmGeneralActivity;
import com.example.spotlight.graduates.GraduatesProposeActivity;
import com.example.spotlight.profile.ProfileGraduatesActivity;
import com.example.spotlight.network.Util.TokenManager;
import com.example.spotlight.posting.ManagePostingGeneralActivity;
import com.example.spotlight.profile.ProfileGeneralActivity;

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

        Log.d("MyPageFagment", "loaded");
        ImageView imageViewProfile = view.findViewById(R.id.mypage_user_image);
        TextView username = view.findViewById(R.id.mypage_user_name);
        String usernameStr = TokenManager.getUsername();
        String profileImg = TokenManager.getProfileImage();
        username.setText(usernameStr);
        if(profileImg != null && !profileImg.isEmpty()) {
            RequestOptions requestOptions = new RequestOptions()
                    .override(100, 100)
                    .circleCrop();

            Glide.with(this)
                    .load(profileImg)
                    .apply(requestOptions)
                    .into(imageViewProfile);
        }

        return view;
    }
    public void onAlarmClicked(View view) {
        if (getActivity() != null) {
            String userType = TokenManager.getRole();

            Intent intent;
            if ("STUDENT".equals(userType)) {
                intent = new Intent(getActivity(), AlarmActivity.class);
            } else {
                intent = new Intent(getActivity(), AlarmGeneralActivity.class);
            }

            startActivity(intent);
        }
    }

    public void onProfileClicked(View view) {
        if (getActivity() != null) {
            String userType = TokenManager.getRole();

            Intent intent;
            if ("STUDENT".equals(userType)) {
                intent = new Intent(getActivity(), ProfileGraduatesActivity.class);
            } else {
                intent = new Intent(getActivity(), ProfileGeneralActivity.class);
            }

            startActivity(intent);
        }
    }

    public void onManagePostingClicked(View view) {
        if (getActivity() != null) {
            String userType = TokenManager.getRole();

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
            String userType = TokenManager.getRole();

            Intent intent;
            if ("STUDENT".equals(userType)) {
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
        try{
            if (getActivity() != null) {
                String userType = TokenManager.getRole();

                Intent intent;
                if ("STUDENT".equals(userType)) {
                    intent = new Intent(getActivity(), GraduatesProposeActivity.class);
                } else {
                    intent = new Intent(getActivity(), GeneralProposeManageActivity.class);
                }

                startActivity(intent);
            }
        }catch (Exception e) {
            Log.d("exception", e.getMessage());
        }
    }
}


