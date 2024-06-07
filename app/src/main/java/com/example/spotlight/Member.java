package com.example.spotlight;

public class Member {
    private int imageResource;
    private String name;

    public Member(int imageResource, String name) {
        this.imageResource = imageResource;
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }
}
