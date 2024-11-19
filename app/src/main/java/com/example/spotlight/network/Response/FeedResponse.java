package com.example.spotlight.network.Response;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class FeedResponse {
    private boolean success;
    private String message;
    private Post post;

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public static class Post {
        private Long feedId;
        private String title;
        private String thumbnailImage;
        private List<String> feedImages;
        private String content;
        private Integer scrap;
        private Integer hitsUser;
        private Integer hitsRecruiter;
        private Timestamp createdDate;
        private Timestamp modifiedDate;
        private Category category;
        private Member user;
        private Exhibition exhibition;
        private Project project;
        private Set<Hashtag> hashtags;

        // Getters and Setters
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

        public String getThumbnailImage() {
            return thumbnailImage;
        }

        public void setThumbnailImage(String thumbnailImage) {
            this.thumbnailImage = thumbnailImage;
        }

        public List<String> getFeedImages() {
            return feedImages;
        }

        public void setFeedImages(List<String> feedImages) {
            this.feedImages = feedImages;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Integer getScrap() {
            return scrap;
        }

        public void setScrap(Integer scrap) {
            this.scrap = scrap;
        }

        public Integer getHitsUser() {
            return hitsUser;
        }

        public void setHitsUser(Integer hitsUser) {
            this.hitsUser = hitsUser;
        }

        public Integer getHitsRecruiter() {
            return hitsRecruiter;
        }

        public void setHitsRecruiter(Integer hitsRecruiter) {
            this.hitsRecruiter = hitsRecruiter;
        }

        public Timestamp getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Timestamp createdDate) {
            this.createdDate = createdDate;
        }

        public Timestamp getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(Timestamp modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Member getUser() {
            return user;
        }

        public void setUser(Member user) {
            this.user = user;
        }

        public Exhibition getExhibition() {
            return exhibition;
        }

        public void setExhibition(Exhibition exhibition) {
            this.exhibition = exhibition;
        }

        public Project getProject() {
            return project;
        }

        public void setProject(Project project) {
            this.project = project;
        }

        public Set<Hashtag> getHashtags() {
            return hashtags;
        }

        public void setHashtags(Set<Hashtag> hashtags) {
            this.hashtags = hashtags;
        }

        // Nested classes
        public static class Category {
            private Long id;
            private String name;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class Member {
            private Long id;
            private String name;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class Exhibition {
            private Long exhibitionId;
            private String location;
            private String schedule;
            private String time;

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
        }

        public static class Project {
            private Long id;
            private String name;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class Hashtag {
            private Long id;
            private String hashtag;

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
        }
    }
}