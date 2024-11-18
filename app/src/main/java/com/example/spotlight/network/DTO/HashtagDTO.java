package com.example.spotlight.network.DTO;

import java.sql.Timestamp;
import java.util.Set;

public class HashtagDTO {

    private Long id;
    private String hashtag;
    private Timestamp createdDate;
    private HashtagUserDTO user;
    private Set<HashtagFeedDTO> feeds;

    // 기본 생성자
    public HashtagDTO() {}

    // Getter와 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public HashtagUserDTO getUser() {
        return user;
    }

    public void setUser(HashtagUserDTO user) {
        this.user = user;
    }

    public Set<HashtagFeedDTO> getFeeds() {
        return feeds;
    }

    public void setFeeds(Set<HashtagFeedDTO> feeds) {
        this.feeds = feeds;
    }

    // 내부 클래스 정의
    public static class HashtagUserDTO {
        private Long id;          // 사용자 ID
        private String username;  // 사용자 이름

        // 기본 생성자
        public HashtagUserDTO() {}

        // Getter와 Setter
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class HashtagFeedDTO {
        private Long feedId;      // 피드 ID
        private String title;     // 피드 제목

        // 기본 생성자
        public HashtagFeedDTO() {}

        // Getter와 Setter
        public Long getFeedId() {
            return feedId;
        }

        public void setFeedId(Long feedId) {
            this.feedId = feedId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}