package br.com.jpcchaves.core.exception.enums;

import br.com.jpcchaves.core.domain.constants.HttpStatus;

public enum ExceptionDefinition {
  GEN0001("Resource not found!", "GEN-0001", HttpStatus.NOT_FOUND),
  GEN0002("Bad request. Check the request and try again!", "GEN-0002",
      HttpStatus.BAD_REQUEST),
  JWT0001("Malformed Jwt!", "JWT-0001", HttpStatus.BAD_REQUEST),
  JWT0002("Expired Jwt!", "JWT-0002", HttpStatus.BAD_REQUEST),
  JWT0003("Invalid token format!", "JWT-0003", HttpStatus.BAD_REQUEST),
  JWT0004("Token not found!", "JWT-0004", HttpStatus.BAD_REQUEST),
  JWT0005("Invalid token signature!", "JWT-005",
      HttpStatus.BAD_REQUEST), USR0001(
      "User not found with the given email!", "USR-0001",
      HttpStatus.NOT_FOUND), INT0001(
      "Internal server error! Contact admin to get more details", "INT-0001",
      HttpStatus.INTERNAL_SERVER_ERROR);


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
