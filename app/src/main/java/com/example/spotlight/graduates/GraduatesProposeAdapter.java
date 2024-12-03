package com.example.spotlight.graduates;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.example.spotlight.network.DTO.ProposalDTO;
import com.example.spotlight.network.Response.ProposalResponse;
import com.example.spotlight.proposal.ProposalDetailsActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        Log.d("GraduatesProposeAdapter", "Binding item with proposalId: " + proposal.getProposalId());

        holder.companyName.setText(companyName);
        holder.role.setText(proposal.getJob());
        holder.proposeDate.setText(proposal.getFormattedDate());

        String formattedTimeAgo = getTimeAgo(proposal.getFormattedDate());
        holder.proposeDate.setText(formattedTimeAgo);

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

        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(v -> {
            Log.d("GraduatesProposeAdapter", "Item clicked: " + proposal.getProposalId());

            Intent intent = new Intent(context, ProposalDetailsActivity.class);
            intent.putExtra("proposalId", proposal.getProposalId());
            context.startActivity(intent);
        });
    }

    private String getTimeAgo(String dateString) {
        try {
            // Parse the date from the string
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            Date date = sdf.parse(dateString);

            if (date != null) {
                long diffInMillis = System.currentTimeMillis() - date.getTime();
                return DateUtils.getRelativeTimeSpanString(date.getTime(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }

    @Override
    public int getItemCount() {
        return proposals.size();
    }

    // ViewHolder 정의
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