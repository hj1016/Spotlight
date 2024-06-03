package com.example.spotlight.network.Response;

import java.util.List;

public class HashtagHistoryResponse {
    private boolean success;
    private List<String> history;

    public HashtagHistoryResponse(boolean success, List<String> history) {
        this.success = success;
        this.history = history;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }
}