package com.example.spotlight.network.Request;

import com.example.spotlight.network.DTO.ProjectDTO;
import com.example.spotlight.network.DTO.TeamDTO;

import java.util.List;

public class FeedRequest {
    private String title;
    private String image;
    private String content;
    private int scrap;
    private Category category;
    private List<String> hashtag;
    private Exhibition exhibition;
    private ProjectDTO projectId;
    private TeamDTO teamId;

    // Category 내부 클래스
    public static class Category {
        private String main;
        private String sub;

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

    // Exhibition 내부 클래스
    public static class Exhibition {
        private String location;
        private String schedule;
        private String time;

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

    // Getter와 Setter 메서드들
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

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public ProjectDTO getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectDTO projectId) {
        this.projectId = projectId;
    }

    public TeamDTO getTeamId() { return teamId; }

    public void setTeamId(TeamDTO teamId) { this.teamId = teamId; }
}