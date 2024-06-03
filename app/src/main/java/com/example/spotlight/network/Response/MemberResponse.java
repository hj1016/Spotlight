package com.example.spotlight.network.Response;

import java.util.List;

public class MemberResponse {
    private boolean success;
    private MemberData data;

    // Getters and Setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public MemberData getData() {
        return data;
    }

    public void setData(MemberData data) {
        this.data = data;
    }

    public static class MemberData {
        private String name;
        private String department;
        private List<Project> projects;
        private int scrap_count;

        // Getters and Setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public List<Project> getProjects() {
            return projects;
        }

        public void setProjects(List<Project> projects) {
            this.projects = projects;
        }

        public int getScrap_count() {
            return scrap_count;
        }

        public void setScrap_count(int scrap_count) {
            this.scrap_count = scrap_count;
        }
    }

    public static class Project {
        private String name;
        private Category category;
        private String role;

        // Getters and Setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    public static class Category {
        private String main;

        // Getters and Setters

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }
}