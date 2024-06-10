package com.example.spotlight;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;

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
        return new PostViewHolder(view, this);
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭된 아이템의 위치(position)을 가져옵니다.
                int position = holder.getAdapterPosition();
                // 해당 위치에 있는 피드를 가져옵니다.
                Post clickedPost = posts.get(position);

                // 클릭된 피드의 상세 정보를 보여주는 화면으로 이동합니다.
                Intent intent = new Intent(context, ItemDetailActivity.class);
                // 클릭된 피드의 정보를 인텐트에 직렬화하여 전달합니다.
                intent.putExtra("post", clickedPost);
                context.startActivity(intent);
            }
        });

        holder.scrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.toggleScrap();
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    private void addHashtags(List<String> hashtags, FlexboxLayout flexboxLayout) {
        flexboxLayout.removeAllViews();
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
            layoutParams.setMargins(10, 5, 10, 20);
            textView.setLayoutParams(layoutParams);

            textView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SearchResultActivity.class);
                intent.putExtra("hashtag", hashtag);
                context.startActivity(intent);
            });

            flexboxLayout.addView(textView);
        }
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        // PostAdapter 인스턴스 변수 추가
        private PostAdapter adapter;
        public ImageView team_image, image, scrapButton;
        public TextView title, category, content, scrap;
        public FlexboxLayout flexboxLayout;
        boolean isScrapped = false;

        public View itemBox;

        // PostAdapter 인스턴스를 받아오는 생성자 추가
        public PostViewHolder(View itemView, PostAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
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
            // 클릭된 아이템의 위치(position)을 가져옵니다.
            int position = getAdapterPosition();
            // 해당 위치에 있는 피드를 가져옵니다.
            Post clickedPost = adapter.getItem(position);

            // 클릭된 피드의 상세 정보를 보여주는 화면으로 이동합니다.
            Intent intent = new Intent(itemView.getContext(), ItemDetailActivity.class);
            // 클릭된 피드의 정보를 인텐트에 직렬화하여 전달합니다.
            intent.putExtra("post", clickedPost);
            itemView.getContext().startActivity(intent);
        }

        public void toggleScrap() {
            isScrapped = !isScrapped;
            scrapButton.setImageResource(isScrapped ? R.drawable.scrap_yes : R.drawable.scrap_no);
        }
    }

    public Post getItem(int position) {
        return posts.get(position);
    }
}