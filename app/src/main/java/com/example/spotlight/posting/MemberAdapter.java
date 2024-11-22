package com.example.spotlight.posting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {
    private final List<Member> memberList;

    // 생성자
    public MemberAdapter(List<Member> memberList) {
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
        // 현재 멤버 가져오기
        Member member = memberList.get(position);

        // 멤버 정보 설정
        holder.memberImage.setImageResource(member.getImageResource());
        holder.memberName.setText(member.getName());
        holder.memberRole.setText(member.getRole());
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    // ViewHolder 클래스
    public static class MemberViewHolder extends RecyclerView.ViewHolder {
        public ImageView memberImage; // 멤버 이미지
        public TextView memberName;   // 멤버 이름
        public TextView memberRole;   // 멤버 역할

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            // View 객체 초기화
            memberImage = itemView.findViewById(R.id.member_image);
            memberName = itemView.findViewById(R.id.member_id);
            memberRole = itemView.findViewById(R.id.member_role);
        }
    }
}