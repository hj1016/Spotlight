package com.example.spotlight.posting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;
import com.example.spotlight.network.DTO.FeedDTO;
import com.example.spotlight.network.Util.TokenManager;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {
    private final List<Member> memberList;
    private final FeedDTO feedDTO;

    // 생성자
    public MemberAdapter(List<Member> memberList, FeedDTO feedDTO) {
        this.memberList = memberList;
        this.feedDTO = feedDTO;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        // FeedDTO의 ProjectRoleDTO 가져오기
        FeedDTO.FeedProjectDTO.ProjectRoleDTO roleDTO = feedDTO.getProject().getProjectRoles().get(position);
        Log.d("AdapterData", "ProjectRoleDTO: " + roleDTO);

        // UI에 데이터 설정
        holder.memberName.setText(roleDTO.getUserId().toString());
        holder.memberRole.setText(roleDTO.getRole());
        holder.memberImage.setImageResource(R.drawable.member_image); // 기본 이미지

        // 클릭 이벤트
        holder.itemView.setOnClickListener(v -> {
            String userType = TokenManager.getRole();
            Long userId = roleDTO.getUserId();
            Long feedId = feedDTO.getFeedId();

            if (userId == null || feedId == null || feedId == -1L) {
                Log.e("Intent Data Error", "Invalid data: userId or feedId is null/invalid");
                Toast.makeText(v.getContext(), "Invalid data for navigation", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent;
            if ("RECRUITER".equals(userType)) {
                intent = new Intent(v.getContext(), ItemDetailMemberRecruiterActivity.class);
                intent.putExtra("studentId", userId);
            } else {
                intent = new Intent(v.getContext(), ItemDetailMemberGeneralActivity.class);
                intent.putExtra("userId", userId);
            }

            intent.putExtra("feedId", feedId);
            v.getContext().startActivity(intent);
        });
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
            memberName = itemView.findViewById(R.id.member_name);
            memberRole = itemView.findViewById(R.id.member_role);
        }
    }
}