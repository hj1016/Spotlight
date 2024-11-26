package com.example.spotlight.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;
import com.example.spotlight.network.DTO.FeedDTO;
import com.example.spotlight.posting.Post;
import com.example.spotlight.posting.PostAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> posts = new ArrayList<>();
    private TextView searchResultTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        // View 초기화
        initViews();

        // Intent 데이터 처리
        handleIntentData(getIntent());
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView_search_result);
        searchResultTitle = findViewById(R.id.search_result_title);

        // RecyclerView 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(postAdapter);
    }

    private void handleIntentData(Intent intent) {
        if (intent == null) {
            Toast.makeText(this, "데이터가 전달되지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 전달된 데이터 읽기
        List<FeedDTO> searchResults = (List<FeedDTO>) intent.getSerializableExtra("searchResults");
        String hashtag = intent.getStringExtra("hashtag");
        String searchTerm = intent.getStringExtra("searchTerm");
        String school = intent.getStringExtra("school");
        String major = intent.getStringExtra("major");

        // 제목 설정
        setSearchResultTitle(hashtag, searchTerm, school, major);

        // 검색 결과 설정
        if (searchResults != null && !searchResults.isEmpty()) {
            posts.clear();
            posts.addAll(convertFeedDTOToPost(searchResults)); // FeedDTO -> Post 변환
            postAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSearchResultTitle(String hashtag, String searchTerm, String school, String major) {
        if (hashtag != null) {
            searchResultTitle.setText(hashtag);
        } else if (searchTerm != null) {
            searchResultTitle.setText("검색어: " + searchTerm);
        } else if (school != null && major != null) {
            searchResultTitle.setText(school + " " + major);
        } else if (school != null) {
            searchResultTitle.setText(school);
        } else if (major != null) {
            searchResultTitle.setText(major);
        } else {
            searchResultTitle.setText("검색 결과");
        }
    }

    private List<Post> convertFeedDTOToPost(List<FeedDTO> feedDTOList) {
        List<Post> postList = new ArrayList<>();
        for (FeedDTO feed : feedDTOList) {
            Post post = new Post(
                    feed.getFeedId(),
                    feed.getThumbnailImage(),
                    feed.getTitle(),
                    feed.getCategory().getName(),
                    feed.getFeedImages(),
                    feed.getContent(),
                    feed.getScrap(),
                    feed.isScrapped(),
                    convertHashtags(feed.getHashtags())
            );
            postList.add(post);
        }
        return postList;
    }

    private List<String> convertHashtags(Set<FeedDTO.FeedHashtagDTO> hashtags) {
        List<String> hashtagList = new ArrayList<>();
        if (hashtags != null) {
            for (FeedDTO.FeedHashtagDTO hashtag : hashtags) {
                hashtagList.add(hashtag.getHashtag());
            }
        }
        return hashtagList;
    }

    public void onBackClicked(View view) {
        finish();
    }
}