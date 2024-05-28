package com.example.spotlight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.GraduatesProposal;


import com.bumptech.glide.Glide; // 이미지 로딩을 위한 Glide 라이브러리

import java.util.List;


public class GraduatesProposeAdapter extends RecyclerView.Adapter<GraduatesProposeAdapter.ViewHolder> {
    private List<GraduatesProposal> proposals;
    private Context context;

    public GraduatesProposeAdapter(Context context, List<GraduatesProposal> proposals) {
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
        GraduatesProposal proposal = proposals.get(position);
        holder.companyName.setText(proposal.getCompanyName());
        holder.role.setText(proposal.getRole());
        holder.proposeDate.setText(proposal.getProposeDate());
        // 이미지 설정 예시 (Glide 라이브러리 사용)
        Glide.with(context).load(proposal.getPhotoUrl()).into(holder.photo);
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

