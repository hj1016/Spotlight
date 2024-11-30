package com.example.spotlight.network.Response;

import java.util.List;

public class NotificationListResponse {
    private List<NotificationResponse> notifications;

    public List<NotificationResponse> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationResponse> notifications) {
        this.notifications = notifications;
    }
}