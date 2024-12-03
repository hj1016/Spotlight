package com.example.spotlight.posting;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.ScrapResponse;
import com.example.spotlight.network.Util.TokenManager;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Post> posts;

    // 생성자
    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;

        Log.d("PostAdapter", "Context: " + context);
    }
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post post = posts.get(position);

        // 아이템 클릭 이벤트로 상세 화면으로 이동
        holder.itemView.setOnClickListener(v -> {
            Log.d("PostAdapter", "Item clicked: " + post.getFeedId());
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra("feedId", post.getFeedId());
            context.startActivity(intent);
        });

        // 데이터를 ViewHolder에 바인딩
        Glide.with(context).load(post.getThumbnailImage()).into(holder.thumbnailImage);
        Glide.with(context).load(post.getFeedImages()).into(holder.image);
        holder.title.setText(post.getTitle());
        holder.category.setText(post.getCategory().getName());
        holder.content.setText(post.getContent());
        holder.scrap.setText(String.valueOf(post.getScrap()));
        holder.scrapButton.setImageResource(post.isScrapped() ? R.drawable.scrap_yes : R.drawable.scrap_no);

        // hashtags라는 string으로 된 리스트 생성
        List<Post.Hashtag> hashtags = post.getHashtags();
        List<String> tags = new ArrayList<>();
        for (Post.Hashtag hashtag : hashtags) {
            tags.add(hashtag.getHashtag());
        }

        // 해시태그 Flexbox에 추가
        addHashtags(tags, holder.flexboxLayout);

        // 스크랩 버튼 클릭 리스너
        holder.scrapButton.setOnClickListener(v -> toggleScrap(holder, post));
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
        public ImageView thumbnailImage, scrapButton, image;
        public TextView title, category, content, scrap;
        public FlexboxLayout flexboxLayout;
        private boolean isScrapped = false;

        public PostViewHolder(View itemView) {
            super(itemView);

            thumbnailImage = itemView.findViewById(R.id.team_image);
            scrapButton = itemView.findViewById(R.id.scrap_selection);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
            image = itemView.findViewById(R.id.image);
            content = itemView.findViewById(R.id.content);
            scrap = itemView.findViewById(R.id.scrap);
            flexboxLayout = itemView.findViewById(R.id.main_flexbox_hashtags);
        }
    }

    private void toggleScrap(PostViewHolder holder, Post post) {
        boolean isCurrentlyScrapped = post.isScrapped();
        TokenManager.setScrapStatus(post.getFeedId(), !isCurrentlyScrapped);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ScrapResponse> call = isCurrentlyScrapped
                ? apiService.unscrapFeed(post.getFeedId(), null, null)
                : apiService.scrapFeed(post.getFeedId(), null, null);

        call.enqueue(new Callback<ScrapResponse>() {
            @Override
            public void onResponse(Call<ScrapResponse> call, Response<ScrapResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ScrapResponse scrapResponse = response.body();
                    post.setScrapped(!isCurrentlyScrapped); // 상태 업데이트
                    post.setScrap(scrapResponse.getScrapCount());
                    notifyItemChanged(holder.getAdapterPosition());
                    holder.scrapButton.setImageResource(post.isScrapped() ? R.drawable.scrap_yes : R.drawable.scrap_no);
                    holder.scrap.setText(String.valueOf(post.getScrap()));
                }
            }

            @Override
            public void onFailure(Call<ScrapResponse> call, Throwable t) {
                Toast.makeText(context, "스크랩 요청 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}