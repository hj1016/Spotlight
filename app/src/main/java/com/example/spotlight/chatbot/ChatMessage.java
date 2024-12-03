package com.example.spotlight.chatbot;

import com.example.spotlight.network.DTO.FeedRecommendationDTO;
import com.example.spotlight.network.DTO.StudentRecommendationDTO;

public class ChatMessage {
    public static final int TYPE_USER = 0;
    public static final int TYPE_BOT = 1;
    public static final int TYPE_BOT_PROJECT = 2;
    public static final int TYPE_BOT_STUDENT = 3;

    private int type;
    private String message;
    private FeedRecommendationDTO project;
    private StudentRecommendationDTO student;

    public ChatMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public FeedRecommendationDTO getProject() {
        return project;
    }

    public void setProject(FeedRecommendationDTO project) {
        this.project = project;
    }

    public StudentRecommendationDTO getStudent() {
        return student;
    }

    public void setStudent(StudentRecommendationDTO student) {
        this.student = student;
    }
}