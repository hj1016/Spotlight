package com.example.spotlight.network.DTO;

public class StageDTO {

    private Long id; // 스테이지 ID

    public StageDTO() {}

    public StageDTO(Long id) {
        this.id = id;
    }

    // Getter와 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}