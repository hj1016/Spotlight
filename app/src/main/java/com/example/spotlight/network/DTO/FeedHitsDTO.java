package com.example.spotlight.network.DTO;

public class FeedHitsDTO {
    private Long feedId;
    private int hitsUser;
    private int hitsRecruiter;

    public FeedHitsDTO() {}

    public FeedHitsDTO(Long feedId, int hitsUser, int hitsRecruiter) {
        this.feedId = feedId;
        this.hitsUser = hitsUser;
        this.hitsRecruiter = hitsRecruiter;
    }

    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    public int getHitsUser() {
        return hitsUser;
    }

    public void setHitsUser(int hitsUser) {
        this.hitsUser = hitsUser;
    }

    public int getHitsRecruiter() {
        return hitsRecruiter;
    }

    public void setHitsRecruiter(int hitsRecruiter) {
        this.hitsRecruiter = hitsRecruiter;
    }
}