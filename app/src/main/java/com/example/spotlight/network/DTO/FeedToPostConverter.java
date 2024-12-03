package com.example.spotlight.network.DTO;

import com.example.spotlight.posting.Post;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FeedToPostConverter {

    public static Post convertToPost(FeedDTO feedDTO) {
        if (feedDTO == null) {
            return null;
        }

        return new Post(
                feedDTO.getFeedId(),
                feedDTO.getThumbnailImage(),
                feedDTO.getTitle(),
                feedDTO.getContent(),
                feedDTO.getScrap(),
                toCategory(feedDTO.getCategory()),
                feedDTO.getFeedImg(),
                feedDTO.getFeedImages(),
                toHashtagList(feedDTO.getHashtags()),
                feedDTO.isScrapped(),
                feedDTO.getCreatedDate(),
                feedDTO.getModifiedDate(),
                toUser(feedDTO.getUser()),
                toExhibition(feedDTO.getExhibition())
        );
    }

    // Category
    public static Post.Category toCategory(FeedDTO.FeedCategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        return new Post.Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getParentId());
    }

    // Hashtag
    public static Post.Hashtag toHashtag(FeedDTO.FeedHashtagDTO feedHashtagDTO) {
        if (feedHashtagDTO == null) {
            return null;
        }
        return new Post.Hashtag(feedHashtagDTO.getId(), feedHashtagDTO.getHashtag());
    }

    // Set<FeedHashtagDTO>를 List<Hashtag>로 변환
    public static List<Post.Hashtag> toHashtagList(Set<FeedDTO.FeedHashtagDTO> feedHashtagDTOSet) {
        if (feedHashtagDTOSet == null) {
            return null;
        }
        return feedHashtagDTOSet.stream()
                .map(FeedToPostConverter::toHashtag)
                .collect(Collectors.toList());
    }

    // User
    public static Post.User toUser(FeedDTO.FeedUserDTO feedUserDTO) {
        if (feedUserDTO == null) {
            return null;
        }
        return new Post.User(feedUserDTO.getId(), feedUserDTO.getName());
    }

    // Exhibition
    public static Post.Exhibition toExhibition(FeedDTO.FeedExhibitionDTO feedExhibitionDTO) {
        if (feedExhibitionDTO == null) {
            return null;
        }
        return new Post.Exhibition(
                feedExhibitionDTO.getExhibitionId(),
                feedExhibitionDTO.getLocation(),
                feedExhibitionDTO.getSchedule(),
                feedExhibitionDTO.getTime(),
                feedExhibitionDTO.getUserId(),
                feedExhibitionDTO.getFeedId()
        );
    }
}
