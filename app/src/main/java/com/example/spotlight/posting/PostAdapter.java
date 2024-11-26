package com.example.spotlight.posting;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Post> posts;

    // 생성자
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

        // 데이터를 ViewHolder에 바인딩
        Glide.with(context).load(post.getThumbnailImage()).into(holder.thumbnailImage);
        holder.title.setText(post.getTitle());
        holder.category.setText(post.getCategory());
        holder.content.setText(post.getContent());
        holder.scrap.setText(String.valueOf(post.getScrapCount()));
        holder.scrapButton.setImageResource(post.isScrapped() ? R.drawable.scrap_yes : R.drawable.scrap_no);

        // 해시태그 Flexbox에 추가
        addHashtags(post.getHashtags(), holder.flexboxLayout);

        // 스크랩 버튼 클릭 리스너
        holder.scrapButton.setOnClickListener(v -> holder.toggleScrap());

        // 아이템 클릭 이벤트로 상세 화면으로 이동
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra("feedId", post.getFeedId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return (posts != null) ? posts.size() : 0;
    }

    // 해시태그를 FlexboxLayout에 추가하는 메소드
    private void addHashtags(List<String> hashtags, FlexboxLayout flexboxLayout) {
        flexboxLayout.removeAllViews(); // 이전 뷰 제거

        if (hashtags == null || hashtags.isEmpty()) return;

        for (String hashtag : hashtags) {
            TextView textView = new TextView(context);
            textView.setText("#" + hashtag);
            textView.setTextSize(16); // SP 단위
            textView.setTextColor(ContextCompat.getColor(context, R.color.white));
            textView.setBackground(ContextCompat.getDrawable(context, R.drawable.hashtag_box));
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(20, 10, 20, 10);

            // 레이아웃 매개변수 설정
            FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(10, 5, 10, 20);
            textView.setLayoutParams(layoutParams);

            flexboxLayout.addView(textView);
        }
    }

    public void setData(List<Post> newData) {
        if (posts != null) {
            posts.clear();
            posts.addAll(newData);
        } else {
            posts = newData;
        }
        notifyDataSetChanged();
    }

    // ViewHolder 클래스
    public static class PostViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnailImage, scrapButton;
        public TextView title, category, content, scrap;
        public FlexboxLayout flexboxLayout;
        private boolean isScrapped = false;

        public PostViewHolder(View itemView) {
            super(itemView);

            thumbnailImage = itemView.findViewById(R.id.team_image);
            scrapButton = itemView.findViewById(R.id.scrap_selection);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
            content = itemView.findViewById(R.id.content);
            scrap = itemView.findViewById(R.id.scrap);
            flexboxLayout = itemView.findViewById(R.id.main_flexbox_hashtags);
        }

        public void toggleScrap() {
            isScrapped = !isScrapped;
            scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);

            int currentScrapCount = Integer.parseInt(scrap.getText().toString());
            scrap.setText(String.valueOf(isScrapped ? currentScrapCount + 1 : currentScrapCount - 1));
        }
    }
}