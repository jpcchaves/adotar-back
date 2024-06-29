package br.com.jpcchaves.core.exception.enums;

import br.com.jpcchaves.core.domain.constants.HttpStatus;

public enum ExceptionDefinition {
  GEN0001("Resource not found!", "GEN-0001", HttpStatus.NOT_FOUND),
  GEN0002("Bad request. Check the request and try again!", "GEN-0002",
      HttpStatus.BAD_REQUEST);

  private String message;
  private String code;
  private int httpStatus;

  ExceptionDefinition(
      String message,
      String code,
      int httpStatus
  ) {
    this.message = message;
    this.code = code;
    this.httpStatus = httpStatus;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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
