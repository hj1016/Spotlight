package com.example.spotlight.network.DTO;

import java.util.HashSet;
import java.util.Set;

public class CategoryDTO {

    private Long id;
    private String name;
    private Long parentId;
    private Set<CategoryFeedDTO> feeds = new HashSet<>();

    public CategoryDTO() {}

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Set<CategoryFeedDTO> getFeeds() {
        return feeds;
    }

    public void setFeeds(Set<CategoryFeedDTO> feeds) {
        this.feeds = feeds;
    }

    public static class CategoryFeedDTO {
        private Long feedId;
        private String title;

        public CategoryFeedDTO() {}

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