package com.example.spotlight.network.DTO;

public class PasswordValidationResponseDTO {
    private boolean isValid;
    private String message;

    // 기본 생성자
    public PasswordValidationResponseDTO() {
    }

    // 파라미터가 있는 생성자
    public PasswordValidationResponseDTO(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    // Getter와 Setter
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
