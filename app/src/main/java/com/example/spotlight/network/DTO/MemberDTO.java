package com.example.spotlight.network.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MemberDTO implements Parcelable {

    private String name;
    private String school;
    private String major;
    private List<ProjectInfoDTO> projects;

    public MemberDTO() {}

    public MemberDTO(String name, String school, String major, List<ProjectInfoDTO> projects) {
        this.name = name;
        this.school = school;
        this.major = major;
        this.projects = projects;
    }

    protected MemberDTO(Parcel in) {
        name = in.readString();
        school = in.readString();
        major = in.readString();
        projects = new ArrayList<>();
        in.readList(projects, ProjectInfoDTO.class.getClassLoader());
    }

    public static final Creator<MemberDTO> CREATOR = new Creator<MemberDTO>() {
        @Override
        public MemberDTO createFromParcel(Parcel in) {
            return new MemberDTO(in);
        }

        @Override
        public MemberDTO[] newArray(int size) {
            return new MemberDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(school);
        dest.writeString(major);
        dest.writeList(projects);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<ProjectInfoDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectInfoDTO> projects) {
        this.projects = projects;
    }

    public static class ProjectInfoDTO implements Parcelable {

        private String title;         // 프로젝트 제목
        private String thumbnailImage; // 프로젝트 썸네일 URL
        private String category;      // 프로젝트 카테고리
        private String role;          // 학생의 역할

        public ProjectInfoDTO() {}

        public ProjectInfoDTO(String title, String thumbnailImage, String category, String role) {
            this.title = title;
            this.thumbnailImage = thumbnailImage;
            this.category = category;
            this.role = role;
        }

        protected ProjectInfoDTO(Parcel in) {
            title = in.readString();
            thumbnailImage = in.readString();
            category = in.readString();
            role = in.readString();
        }

        public static final Creator<ProjectInfoDTO> CREATOR = new Creator<ProjectInfoDTO>() {
            @Override
            public ProjectInfoDTO createFromParcel(Parcel in) {
                return new ProjectInfoDTO(in);
            }

            @Override
            public ProjectInfoDTO[] newArray(int size) {
                return new ProjectInfoDTO[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(thumbnailImage);
            dest.writeString(category);
            dest.writeString(role);
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

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}