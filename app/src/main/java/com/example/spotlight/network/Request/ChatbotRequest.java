package com.example.spotlight.network.Request;

import java.util.ArrayList;
import java.util.List;

public class ChatbotRequest {

    private String model;
    private List<Message> messages;

    // 기본 생성자
    public ChatbotRequest() {
        this.messages = new ArrayList<>();
    }

    // 필드 초기화 생성자
    public ChatbotRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    // Getter와 Setter
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    // 메시지 추가 메서드
    public void addMessage(String role, String content) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(new Message(role, content));
    }

    // 내부 Message 클래스
    public static class Message {
        private String role;
        private String content;

        // 기본 생성자
        public Message() {}

        // 필드 초기화 생성자
        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        // Getter와 Setter
        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}