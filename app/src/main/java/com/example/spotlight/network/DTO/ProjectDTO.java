package com.example.spotlight.network.DTO;

import java.util.List;
import java.util.Set;

public class ProjectDTO {

    private Long id;
    private String name;
    private ProjectUserDTO creator;
    private Set<ProjectFeedDTO> feeds;
    private List<ProjectProjectRoleDTO> projectRoles;

    // 기본 생성자
    public ProjectDTO() {}

    // Getter와 Setter
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

    public ProjectUserDTO getCreator() {
        return creator;
    }

    public void setCreator(ProjectUserDTO creator) {
        this.creator = creator;
    }

    public Set<ProjectFeedDTO> getFeeds() {
        return feeds;
    }

    public void setFeeds(Set<ProjectFeedDTO> feeds) {
        this.feeds = feeds;
    }

    public List<ProjectProjectRoleDTO> getProjectRoles() {
        return projectRoles;
    }

    public void setProjectRoles(List<ProjectProjectRoleDTO> projectRoles) {
        this.projectRoles = projectRoles;
    }

    // 내부 클래스 정의

    public static class ProjectUserDTO {
        private Long id;    // 사용자 ID
        private String name; // 사용자 이름

        // 기본 생성자
        public ProjectUserDTO() {}

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

    public static class ProjectFeedDTO {
        private Long feedId; // 피드 ID
        private String title; // 피드 제목

        // 기본 생성자
        public ProjectFeedDTO() {}

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

    public static class ProjectProjectRoleDTO {
        private Long id;       // 프로젝트 역할 ID
        private String role;   // 역할
        private boolean accepted; // 초대 수락 여부

        // 기본 생성자
        public ProjectProjectRoleDTO() {}

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public boolean isAccepted() {
            return accepted;
        }

        public void setAccepted(boolean accepted) {
            this.accepted = accepted;
        }
    }
}