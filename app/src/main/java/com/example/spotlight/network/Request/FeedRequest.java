package com.example.spotlight.network.Request;

import java.util.List;

public class FeedRequest {
    private String title;
    private String image;
    private String content;
    private int scrap;
    private Member member;
    private Exhibition exhibition;
    private int hits_user;
    private int hits_recruiter;
    private Category category;
    private List<String> hashtag;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScrap() {
        return scrap;
    }

    public void setScrap(int scrap) {
        this.scrap = scrap;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public int getHits_user() {
        return hits_user;
    }

    public void setHits_user(int hits_user) {
        this.hits_user = hits_user;
    }

    public int getHits_recruiter() {
        return hits_recruiter;
    }

    public void setHits_recruiter(int hits_recruiter) {
        this.hits_recruiter = hits_recruiter;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getHashtag() {
        return hashtag;
    }

    public void setHashtag(List<String> hashtag) {
        this.hashtag = hashtag;
    }

    public static class Member {
        private String name;
        private String role;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    public static class Exhibition {
        private String location;
        private String schedule;
        private String time;

        // Getters and Setters
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

    public static class Category {
        private String main;
        private String sub;

        // Getters and Setters
        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getSub() {
            return sub;
        }

        public void setSub(String sub) {
            this.sub = sub;
        }
    }
}