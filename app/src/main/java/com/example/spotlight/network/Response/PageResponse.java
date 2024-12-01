package com.example.spotlight.network.Response;

import java.util.List;

public class PageResponse<T> {
    private List<T> content; // 현재 페이지의 데이터
    private int totalPages;  // 총 페이지 수
    private long totalElements; // 총 데이터 수
    private int size;        // 페이지 크기
    private int number;      // 현재 페이지 번호

    // Getters and Setters
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}