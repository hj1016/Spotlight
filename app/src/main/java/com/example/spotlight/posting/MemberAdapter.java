package com.example.spotlight.posting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;
import com.example.spotlight.network.DTO.MemberDTO;

import java.util.ArrayList;
/*
public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {
    private final ArrayList<MemberDTO> memberList;

    public MemberAdapter(ArrayList<MemberDTO> memberList) {
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        MemberDTO member = memberList.get(position);
        holder.memberName.setText(member.getName());
        holder.memberRole.setText(member.getRole());
        holder.memberImage.setImageResource(R.drawable.member_image);
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder {
        public ImageView memberImage;
        public TextView memberName;
        public TextView memberRole;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            memberImage = itemView.findViewById(R.id.member_image);
            memberName = itemView.findViewById(R.id.member_id);
            memberRole = itemView.findViewById(R.id.member_role);
        }
    }
}

 */