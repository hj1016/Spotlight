package com.example.spotlight.network.Util;

import com.example.spotlight.network.DTO.FeedDTO;
import com.example.spotlight.posting.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FeedDTOConverter {

    public List<Post> convertToPostList(List<FeedDTO> feedDTOs) {
        List<Post> posts = new ArrayList<>();
        for (FeedDTO feedDTO : feedDTOs) {
            Post post = new Post();
            post.setFeedId(feedDTO.getFeedId());
            post.setThumbnailImage(feedDTO.getThumbnailImage());
            post.setTitle(feedDTO.getTitle());
            post.setContent(feedDTO.getContent());
            post.setScrap(feedDTO.getScrap());
            post.setCategory(convertCategory(feedDTO.getCategory()));
            post.setFeedImg(feedDTO.getFeedImg());
            post.setFeedImages(feedDTO.getFeedImages());
            post.setHashtags(convertHashtags(feedDTO.getHashtags()));
            post.setScrapped(feedDTO.isScrapped());
            post.setCreatedDate(feedDTO.getCreatedDate());
            post.setModifiedDate(feedDTO.getModifiedDate());
            post.setUser(convertUser(feedDTO.getUser()));
            post.setExhibition(convertExhibition(feedDTO.getExhibition()));

            posts.add(post);
        }
        return posts;
    }

    private Post.User convertUser(FeedDTO.FeedUserDTO userDTO) {
        if (userDTO == null) return null;
        return new Post.User(userDTO.getId(), userDTO.getName());
    }

    private Post.Category convertCategory(FeedDTO.FeedCategoryDTO categoryDTO) {
        if (categoryDTO == null) return null;
        return new Post.Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getParentId());
    }

    private List<Post.Hashtag> convertHashtags(Set<FeedDTO.FeedHashtagDTO> hashtagDTOs) {
        List<Post.Hashtag> hashtags = new ArrayList<>();
        if (hashtagDTOs != null) {
            for (FeedDTO.FeedHashtagDTO hashtagDTO : hashtagDTOs) {
                hashtags.add(new Post.Hashtag(hashtagDTO.getId(), hashtagDTO.getHashtag()));
            }
        }
        return hashtags;
    }

    private Post.Exhibition convertExhibition(FeedDTO.FeedExhibitionDTO exhibitionDTO) {
        if (exhibitionDTO == null) return null;
        return new Post.Exhibition(
                exhibitionDTO.getExhibitionId(),
                exhibitionDTO.getLocation(),
                exhibitionDTO.getSchedule(),
                exhibitionDTO.getTime(),
                exhibitionDTO.getUserId(),
                exhibitionDTO.getFeedId()
        );
    }
}
