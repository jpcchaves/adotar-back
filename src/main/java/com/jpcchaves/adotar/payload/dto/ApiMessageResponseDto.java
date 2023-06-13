package com.jpcchaves.adotar.payload.dto;

public class ApiMessageResponseDto {
    private String message;

    public ApiMessageResponseDto() {
    }

    public ApiMessageResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
