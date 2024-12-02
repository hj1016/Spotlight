package com.example.spotlight.graduates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;
import com.example.spotlight.graduates.Graduate;

import java.util.List;

public class GraduateAdapter extends RecyclerView.Adapter<GraduateAdapter.ViewHolder> {
    private Context context;
    private List<Graduate> graduateList;
    private OnItemClickListener onItemClickListener;

    // 클릭 리스너 인터페이스
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public GraduateAdapter(Context context, List<Graduate> graduateList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.graduateList = graduateList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_graduates, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Graduate graduate = graduateList.get(position);

        holder.nameTextView.setText(graduate.getName());
        holder.projectTextView.setText(graduate.getFeedTitle());

        // 클릭 리스너 설정
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return graduateList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImageView, scrapIconImageView;
        TextView nameTextView, projectTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.scrap_graduates_photo);
            nameTextView = itemView.findViewById(R.id.scrap_graduates_name);
            projectTextView = itemView.findViewById(R.id.scrap_graduates_project_name);
            scrapIconImageView = itemView.findViewById(R.id.scrap_graduates_scrap_go);
        }
    }
}