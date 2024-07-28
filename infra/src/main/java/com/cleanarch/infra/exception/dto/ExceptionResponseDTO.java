package com.cleanarch.infra.exception.dto;

import java.util.Date;

public class ExceptionResponseDTO {

  private Date timestamp = new Date();
  private String message;
  private String details;

  public ExceptionResponseDTO() {}

  public ExceptionResponseDTO(String message, String details) {

    this.message = message;
    this.details = details;
  }

  public ExceptionResponseDTO(Date timestamp, String message, String details) {
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }

  public ExceptionResponseDTO(Builder builder) {
    this.message = builder.message;
    this.details = builder.details;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  @Override
  public String toString() {
    return "timestamp="
        + timestamp
        + ", message='"
        + message
        + '\''
        + ", details='"
        + details;
  }

  public static class Builder {

    private String message;
    private String details;

    protected Builder() {}

    public ExceptionResponseDTO build() {
      return new ExceptionResponseDTO(this);
    }

    public Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    public Builder setDetails(String details) {
      this.details = details;
      return this;
    }
  }
}
