package com.jpcchaves.adotar.domain.exception;

import com.jpcchaves.adotar.domain.Enum.ExceptionDefinition;

public abstract class BaseException extends RuntimeException {
  private String code;
  private int httpStatus;

  public BaseException(String message,
                       String code,
                       int httpStatus) {
    super(message);
    this.code = code;
    this.httpStatus = httpStatus;
  }

  public BaseException(ExceptionDefinition exceptionDefinition) {
    super(exceptionDefinition.getMessage());
    this.code = exceptionDefinition.getCode();
    this.httpStatus = exceptionDefinition.getHttpStatus();
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(int httpStatus) {
    this.httpStatus = httpStatus;
  }
}
