package com.example.spotlight.graduates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide; // 이미지 로딩을 위한 Glide 라이브러리
import com.example.spotlight.R;
import com.example.spotlight.network.DTO.ProposalDTO;
import com.example.spotlight.network.Response.ProposalResponse;

import java.util.List;


public class GraduatesProposeAdapter extends RecyclerView.Adapter<GraduatesProposeAdapter.ViewHolder> {
    private List<ProposalResponse> proposals;
    private Context context;

    public GraduatesProposeAdapter(Context context, List<ProposalResponse> proposals) {
        this.context = context;
        this.proposals = proposals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_graduates_propose, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProposalResponse proposal = proposals.get(position);

        ProposalDTO.ProposalRecruiterDTO recruiter = proposal.getRecruiter();
        String companyName = recruiter != null ? recruiter.getCompany() : "회사 정보 없음";
        String profileImgUrl = recruiter != null ? recruiter.getProfileImage() : "";

        holder.companyName.setText(companyName);
        holder.role.setText(proposal.getJob());
        holder.proposeDate.setText(proposal.getFormattedDate());

        if (profileImgUrl != null) {
            Glide.with(context)
                    .load(profileImgUrl)
                    .into(holder.photo);  // ImageView에 프로필 이미지 로딩
        } else {
            // 프로필 이미지가 없는 경우 기본 이미지 로딩
            Glide.with(context)
                    .load(R.drawable.image_basic)
                    .into(holder.photo);
        }
    }

    @Override
    public int getItemCount() {
        return proposals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView companyName, proposeDate, role;

        public ViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.item_graduates_propose_photo);
            companyName = itemView.findViewById(R.id.item_graduates_propose_company);
            proposeDate = itemView.findViewById(R.id.item_graduates_propose_date);
            role = itemView.findViewById(R.id.item_graduates_propose_role);
        }
    }
}