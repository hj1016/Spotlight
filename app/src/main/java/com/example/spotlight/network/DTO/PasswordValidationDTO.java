package com.example.spotlight.network.DTO;

public class PasswordValidationDTO {
    private String password;

    // 기본 생성자
    public PasswordValidationDTO() {
    }

    // 파라미터가 있는 생성자
    public PasswordValidationDTO(String password) {
        this.password = password;
    }

    // Getter와 Setter
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
