package com.example.spotlight.network.Response;

public class NotificationResponse {
    private Long notificationId;
    private Long userId;
    private String type;
    private String message;
    private String date;
    private String status;

    public NotificationResponse(Long notificationId, Long userId, String type, String message, String date, String status) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.type = type;
        this.message = message;
        this.date = date;
        this.status = status;
    }

    // Getters and Setters
    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
