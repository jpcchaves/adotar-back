package com.cleanarch.usecase.common.dto;

public class MessageResponseDTO {
  private String message;

  public MessageResponseDTO() {}

  public MessageResponseDTO(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
