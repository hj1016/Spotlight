package com.example.spotlight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post post = posts.get(position);
        Glide.with(context).load(post.getTeamImageUrl()).into(holder.team_image);
        holder.title.setText(post.getTitle());
        holder.category.setText(post.getCategory());
        Glide.with(context).load(post.getImageUrl()).into(holder.image);
        holder.content.setText(post.getContent());
        holder.scrap.setText(String.valueOf(post.getScrap()));
        holder.hashtag.setText(post.getHashtag());
        Glide.with(context).load(post.getScrapImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        public ImageView team_image, image, scrap_selection;
        public TextView title, category, content, scrap, hashtag;

        public PostViewHolder(View itemView) {
            super(itemView);
            team_image = itemView.findViewById(R.id.team_image);
            image = itemView.findViewById(R.id.image);
            scrap_selection = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
            content = itemView.findViewById(R.id.content);
            scrap = itemView.findViewById(R.id.scrap);
            hashtag = itemView.findViewById(R.id.hashtag);
        }
    }
}
