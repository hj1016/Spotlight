package com.example.spotlight.posting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.example.spotlight.network.DTO.MemberDTO;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private List<MemberDTO.ProjectInfoDTO> projects;
    private Context context;
    private FirebaseStorage firebaseStorage;

    public ProjectAdapter(Context context, List<MemberDTO.ProjectInfoDTO> projects) {
        this.context = context;
        this.projects = projects;
        this.firebaseStorage = FirebaseStorage.getInstance();
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        MemberDTO.ProjectInfoDTO project = projects.get(position);

        holder.projectTitle.setText(project.getTitle() != null ? project.getTitle() : "제목 없음");
        holder.projectCategory.setText(project.getCategory() != null ? project.getCategory() : "카테고리 없음");

        // Firebase Storage에서 프로젝트 이미지 로드
        if (project.getThumbnailImage() != null) {
            StorageReference storageReference = firebaseStorage.getReference().child(project.getThumbnailImage());
            storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                Glide.with(context)
                        .load(uri)
                        .placeholder(R.drawable.team_image)
                        .into(holder.projectImage);
            }).addOnFailureListener(e -> {
                holder.projectImage.setImageResource(R.drawable.team_image); // 기본 이미지
            });
        } else {
            holder.projectImage.setImageResource(R.drawable.team_image); // 기본 이미지
        }
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        ImageButton projectBox;
        TextView projectTitle, projectCategory;
        ImageView projectImage;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            projectBox = itemView.findViewById(R.id.item_project_box);
            projectTitle = itemView.findViewById(R.id.item_project_name);
            projectCategory = itemView.findViewById(R.id.item_project_category);
            projectImage = itemView.findViewById(R.id.item_project_photo);
        }
    }
}