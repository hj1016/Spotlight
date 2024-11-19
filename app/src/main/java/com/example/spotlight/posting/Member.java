package com.example.spotlight.posting;

public class Member {
    private int imageResource;
    private String name;
    private String role;

    public Member(int imageResource, String name, String role) {
        this.imageResource = imageResource;
        this.name = name;
        this.role = role;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public String getRole() { return role; }
}
