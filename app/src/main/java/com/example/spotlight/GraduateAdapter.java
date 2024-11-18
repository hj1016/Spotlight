package com.example.spotlight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.spotlight.network.DTO.ScrapDTO;

import java.util.List;

/*
public class GraduateAdapter extends RecyclerView.Adapter<GraduateAdapter.GraduateViewHolder> {
    private Context context;
    private List<ScrapDTO> graduates;

    public GraduateAdapter(Context context, List<ScrapDTO> graduates) {
        this.context = context;
        this.graduates = graduates;
    }

    @Override
    public GraduateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_graduates, parent, false);
        return new GraduateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GraduateViewHolder holder, int position) {
        ScrapDTO scrapDTO = graduates.get(position);
        Glide.with(context).load(scrapDTO.getProfileImage()).circleCrop().into(holder.imageViewGraduatesPhoto);
        holder.textViewGraduatesName.setText(scrapDTO.getUsername());
        holder.textViewProjectName.setText(scrapDTO.getProjectRole());
    }

    @Override
    public int getItemCount() {
        return graduates.size();
    }

    public static class GraduateViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewGraduatesPhoto;
        public TextView textViewGraduatesName;
        public TextView textViewProjectName;

        public GraduateViewHolder(View itemView) {
            super(itemView);
            imageViewGraduatesPhoto = itemView.findViewById(R.id.scrap_graduates_photo);
            textViewGraduatesName = itemView.findViewById(R.id.scrap_graduates_name);
            textViewProjectName = itemView.findViewById(R.id.scrap_graduates_project_name);
        }
    }
}

 */