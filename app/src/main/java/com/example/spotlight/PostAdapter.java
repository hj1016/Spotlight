package com.example.spotlight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.content.Intent;

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


        addHashtags(post.getHashtags(), holder.flexboxLayout);

        holder.scrapButton.setImageResource(post.isScrapped() ? R.drawable.scrap_yes : R.drawable.scrap_no);

        // 스크랩 버튼 클릭 리스너 설정
        holder.scrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.toggleScrap();
            }
        });

        // 전체 itemView에 대한 클릭 리스너 설정
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetailActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    // 해시태그를 FlexboxLayout에 추가하는 메소드
    private void addHashtags(List<String> hashtags, FlexboxLayout flexboxLayout) {
        flexboxLayout.removeAllViews();  // 이전 뷰들을 제거
        for (String hashtag : hashtags) {
            TextView textView = new TextView(context);
            textView.setText("#" + hashtag);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setTextColor(context.getResources().getColor(R.color.white));
            textView.setBackground(context.getResources().getDrawable(R.drawable.hashtag_box));
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(20, 10, 20, 10);

            FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(10, 5, 10, 5);
            textView.setLayoutParams(layoutParams);

            flexboxLayout.addView(textView);
        }
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        public ImageView team_image, image, scrapButton;
        public TextView title, category, content, scrap;
        public FlexboxLayout flexboxLayout;
        boolean isScrapped = false;

        public View itemBox;

        public PostViewHolder(View itemView) {
            super(itemView);
            team_image = itemView.findViewById(R.id.team_image);
            image = itemView.findViewById(R.id.image);
            scrapButton = itemView.findViewById(R.id.scrap_selection);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
            content = itemView.findViewById(R.id.content);
            scrap = itemView.findViewById(R.id.scrap);
            flexboxLayout = itemView.findViewById(R.id.main_flexbox_hashtags);

            itemBox = itemView.findViewById(R.id.item_box);

            itemBox.setOnClickListener(this::onOpenClicked);

        }

        private void onOpenClicked(View v) {
            // 아이템 클릭 로직 (상세 화면으로 이동)
            Intent intent = new Intent(itemView.getContext(), ItemDetailActivity.class);
            itemView.getContext().startActivity(intent);
        }
        public void toggleScrap() {
            isScrapped = !isScrapped;
            scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);
        }
    }
}
