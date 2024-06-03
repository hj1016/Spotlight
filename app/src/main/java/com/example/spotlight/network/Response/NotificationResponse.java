package com.example.spotlight.network.Response;

public class NotificationResponse {
    private int notificationId;
    private String type;
    private String message;
    private String date;
    private String status;

    public NotificationResponse(int notificationId, String type, String message, String date, String status) {
        this.notificationId = notificationId;
        this.type = type;
        this.message = message;
        this.date = date;
        this.status = status;
    }

    // Getters and Setters
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

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
