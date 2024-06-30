package br.com.jpcchaves.core.exception;

import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;

public abstract class BaseException extends RuntimeException {

  private final String code;
  private final int httpStatus;

  public BaseException(
      String message,
      String code,
      int httpStatus
  ) {
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

  public int getHttpStatus() {
    return httpStatus;
  }
}
