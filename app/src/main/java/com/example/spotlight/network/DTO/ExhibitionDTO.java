package com.example.spotlight.network.DTO;

public class ExhibitionDTO {

    private Long exhibitionId;
    private String location;
    private String schedule;
    private String time;
    private ExhibitionUserDTO user; // 사용자 요약 정보
    private ExhibitionFeedDTO feed; // 피드 요약 정보

    // 기본 생성자
    public ExhibitionDTO() {}

    // Getter와 Setter
    public Long getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(Long exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ExhibitionUserDTO getUser() {
        return user;
    }

    public void setUser(ExhibitionUserDTO user) {
        this.user = user;
    }

    public ExhibitionFeedDTO getFeed() {
        return feed;
    }

    public void setFeed(ExhibitionFeedDTO feed) {
        this.feed = feed;
    }

    // 사용자 ID 반환
    public Long getUserId() {
        return (user != null) ? user.getId() : null;
    }

    // 피드 ID 반환
    public Long getFeedId() {
        return (feed != null) ? feed.getFeedId() : null;
    }

    // 사용자 DTO 클래스
    public static class ExhibitionUserDTO {
        private Long id;

        public ExhibitionUserDTO() {}

        public ExhibitionUserDTO(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    // 피드 DTO 클래스
    public static class ExhibitionFeedDTO {
        private Long feedId;
        private String title;

        public ExhibitionFeedDTO() {}

        public ExhibitionFeedDTO(Long feedId, String title) {
            this.feedId = feedId;
            this.title = title;
        }

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