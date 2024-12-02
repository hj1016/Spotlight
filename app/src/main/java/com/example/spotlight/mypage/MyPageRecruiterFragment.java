package com.example.spotlight.mypage;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.spotlight.R;
import com.example.spotlight.network.Util.TokenManager;
import com.example.spotlight.scrap.ScrapGraduatesActivity;
import com.example.spotlight.scrap.ScrapProjectActivity;
import com.example.spotlight.profile.ProfileRecruiterActivity;
import com.example.spotlight.recruiter.RecruiterProposeManageActivity;

import org.w3c.dom.Text;

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

        Log.d("MyPageRecruiterFagment", "loaded");
        ImageView imageViewProfile = view.findViewById(R.id.mypage_recruiter_image);
        TextView username = view.findViewById(R.id.mypage_recruiter_name);
        String usernameStr = TokenManager.getName();
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

    private void onProfileClicked(View view) {
        Intent intent = new Intent(getActivity(), ProfileRecruiterActivity.class);
        startActivity(intent);
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
            Intent intent = new Intent(getActivity(), RecruiterProposeManageActivity.class);
            startActivity(intent);
        }
    }
}
