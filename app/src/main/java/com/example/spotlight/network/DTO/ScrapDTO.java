package com.example.spotlight.network.DTO;

public class ScrapDTO {

    private Long scrapId;
    private ScrapUserDTO user;
    private ScrapFeedDTO feed;
    private ScrapStageDTO stage;
    private ScrappedUserDTO scrappedUser;

    public ScrapDTO() {}

    // Getter와 Setter
    public Long getScrapId() {
        return scrapId;
    }

    public void setScrapId(Long scrapId) {
        this.scrapId = scrapId;
    }

    public ScrapUserDTO getUser() {
        return user;
    }

    public void setUser(ScrapUserDTO user) {
        this.user = user;
    }

    public ScrapFeedDTO getFeed() {
        return feed;
    }

    public void setFeed(ScrapFeedDTO feed) {
        this.feed = feed;
    }

    public ScrapStageDTO getStage() {
        return stage;
    }

    public void setStage(ScrapStageDTO stage) {
        this.stage = stage;
    }

    public ScrappedUserDTO getScrappedUser() {
        return scrappedUser;
    }

    public void setScrappedUser(ScrappedUserDTO scrappedUser) {
        this.scrappedUser = scrappedUser;
    }

    // 내부 클래스 정의
    public static class ScrapUserDTO extends UserBaseDTO {
        public ScrapUserDTO() {}
    }

    public static class ScrappedUserDTO extends UserBaseDTO {
        public ScrappedUserDTO() {}
    }

    public static class UserBaseDTO {
        private Long id;          // 사용자 ID
        private String username;  // 사용자 이름
        private String email;     // 이메일 주소
        private String name;      // 사용자 실명

        public UserBaseDTO() {}

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ScrapFeedDTO {
        private Long id;           // 피드 ID
        private String title;      // 피드 제목
        private String thumbnailImage; // 썸네일 이미지 URL
        private String content;    // 피드 내용

        public ScrapFeedDTO() {}

        // Getter와 Setter
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumbnailImage() {
            return thumbnailImage;
        }

        public void setThumbnailImage(String thumbnailImage) {
            this.thumbnailImage = thumbnailImage;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class ScrapStageDTO {
        private Long id; // 스테이지 ID

        public ScrapStageDTO() {}

        // Getter와 Setter
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}